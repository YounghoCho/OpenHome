/*Graph*/
function goStaticGraphAjax(){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".homeReadDiv").hide();
	$(".staticGraphDiv").show();

	var allTraffics = new Array();
	var readTraffics = new Array();
	var writeTraffics = new Array();
	var downloadTraffics = new Array();
	jQuery.ajax({
		type : "GET",
		url : "api/traffic/trafficData",
		dataType : "json",
		data : "",
		
		success : function(res){
			/*
			 * 그래프 삽입 알고리즘
			 * 
			 * @Author : 조영호
			 * 
			 * 1. 저장된 트래픽 데이터는 0~10자리 (ex 2018-00-00)를 기준으로 한다. (1일기준 8640만 간격)
			 * 2. 시각화를 위해 현재날짜가 아닌, 처음으로 데이터가 들어간 날짜를 시작 날짜로 가정한다.
			 * 3. 날짜 수 만큼 반복하고(Outer-For), 데이터의 개수만큼 반복한다(Inner-For)
			 * 4. 트래픽의 종류에 따라 합(sum)을 계산 및 저장한 후, 그래프에 배열을 전달한다.
			 */
			var length = res.trafficCount;
			var startDate = Date.parse(res.trafficData[0].trafficDate.substring(0,10));//트래픽 시작날짜
			var endDate = Date.parse(res.trafficData[length-1].trafficDate.substring(0,10));//트래픽 최신날짜
	
			graphAlgorithm(allTraffics, "all");
			graphAlgorithm(readTraffics, "read");
			graphAlgorithm(writeTraffics, "write");
			graphAlgorithm(downloadTraffics, "download");
			
			function graphAlgorithm(arr, str){
				var sum = 0;
				var memo = 0;
				for(var index = startDate; index <= endDate; index += 86400000){
					//하루 증가된 날짜와 갱신된 memo값이 들어온다.
					for(i = memo; i < length; i++){						
						//만약 인덱싱날짜와 디비날짜가 같으면 
						if(Date.parse(res.trafficData[i].trafficDate.substring(0,10)) == index){
								if(str == "all")
									sum += res.trafficData[i].trafficContentLength;
								else if(res.trafficData[i].trafficKind == str)
									sum += res.trafficData[i].trafficContentLength;					
						//다르면
						}else{
							memo = i; //memo 갱신
							break;
						}
					}
					/***비동기 알람 처리부분***/
					arr.push(sum);
					sum=0; //sum 갱신
				}				
			}

			//Draw
			Highcharts.chart('container', {
			    title: {
			        text: 'OPENWORKS 트래픽 그래프'
			    },
			    subtitle: {
			        text: '트래픽 그래프는 과금 정책과 직접적인 관련이 있습니다.'
			    },
			    xAxis:{
			    	labels : {
			    		formatter : function(){
			    			return this.value + '일';
			    		}
			    	}
			    },
			    yAxis: {
			        title: {
			            text: 'Traffic'
			        }
			    },
			    legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'middle'
			    },
			    plotOptions: {
			        series: {
			            label: {
			                connectorAllowed: false
			            },
			            pointStart: 10
			        }
			    },
				credits:{enabled: false},			
			    series: [{
			        name: '전체 트래픽',
			        data: allTraffics	
			    }, {
			        name: '읽기 트래픽',
			        data: readTraffics
			    }, {
			        name: '쓰기 트래픽',
			        data: writeTraffics
			    }, {
			        name: '파일 다운로드',
			        data: downloadTraffics
			    }],
			
			    responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 800
			            },
			            chartOptions: {
			                legend: {
			                    layout: 'horizontal',
			                    align: 'center',
			                    verticalAlign: 'bottom'
			                }
			            }
			        }]
			    }
			});
			//end Graph
		},//end success
		error: function(err){
			alert(err);
		}
	});
	history.pushState({ data: '4' }, 'title4', '?depth=4');
}

/*Notice*/
var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("closeNotice")[0];

//이부분을 폴링으로 바꿔야한다.

//알고리즘
//1. 폴링으로 5초에 한번 검사한다 fuc
//2. ajax로 현재 토탈 트래픽을 구한다.
//3. fuc set: 비동기 함수로 처리한다.

//4. fuc1 : if(sum > 6000) { if(sum > 10000) //팝업2(빨간색)} else //팝업1(노란색)
//5. fuc2 : 그래프에서 처럼 배열만들어서 진행한다. 
//6. stack변수를 하나 만들고, i가 도는 for문에서, i-3 i-2 i-1을 넣고if( res.i * 2 > avg(stack) ) //팝업3(보라색)
//7. 팝업 뜰때마다 그래프를 ajax호출로 다시 그려준다.
var poll = 1;
setInterval(function(){ 
	//test
	if(poll==4) 
		poll=1
	eval("test = notice" + poll);
	test();
	poll++;
	/*$.ajax({ 
		url: "server", 
		success: function(res){ 
		},
	});*/
}, 5000);


//알림은 3단계로 구분하며 빨간색(긴급) > 노란색(주의) > 보라색(참고) 순서이다.
function notice1(){
    modal.style.display = "block";
    $(".modal-header > h2").html("Urgent");
    $(".modal-body > p").html("<p>트래픽이 10k를 초과했습니다.</p>");
    $(".modal-header").css("background-color", "crimson");
    $(".modal-footer").css("background-color", "crimson");
}
function notice2(){
    modal.style.display = "block";
    $(".modal-header > h2").html("Warning");
    $(".modal-body > p").html("<p>트래픽이 7k를 초과했습니다.</p>");
    $(".modal-header").css("background-color", "sandybrown");
    $(".modal-footer").css("background-color", "sandybrown");
}
function notice3(){
    modal.style.display = "block";
    $(".modal-header > h2").html("Notice");
    $(".modal-body > p").html("<p>트래픽이 상승하고 있습니다.</p>");
    $(".modal-header").css("background-color", "darkorchid");
    $(".modal-footer").css("background-color", "darkorchid");
}
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}