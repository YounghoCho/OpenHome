//트래픽 알림이 1번만 동작하게 한다
let flag1 = 1;
let flag2 = 1;
let flag3 = 1;

//그래프 그리기.
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
				var queue = new Array();
				var front = 0, rear = 0, interval = 3;				
				for (var index = startDate; index <= endDate; index += 86400000){
					//하루 증가된 날짜와 갱신된 memo값이 들어온다.
					for (i = memo; i < length; i++){						
						//만약 인덱싱날짜와 디비날짜가 같으면 
						if (Date.parse(res.trafficData[i].trafficDate.substring(0,10)) == index){
								if (str == "all"){
									sum += res.trafficData[i].trafficContentLength;								
								}
								else if (res.trafficData[i].trafficKind == str){
									sum += res.trafficData[i].trafficContentLength;	
								}						
						}else { //다르면
							memo = i; //memo 갱신
							break;
						}
					}
					
					/*
					 * 비정상 상승 캐치 알고리즘
					 * 
					 * @Author : 조영호
					 * 
					 * 1. 3일간 발생한 트래픽의 평균보다, 오늘의 트래픽이 2배 이상이면 비정상 트래픽으로 간주한다.
					 * 2. 스택 + 큐 형태의 인덱스가 있는 배열을 사용한다.
					 * 3. 하루씩 데이터를 갱신하며 front, rear 인덱스를 증가시킨다.
					 * 4. 비정상 트래픽이 감지되면 알람이 발생한다.
					 * 3. 최대 30개의 배열 인덱스가 생성된다. 
					 */
					if (str == "all"){
						if (rear < (interval + front)){
							queue.push(sum);
							rear++;
						}else {
							let temp = 0;
							for (let i = front; i < rear; i++){
								temp += queue[i];
							}
							if (sum > (temp / interval) * 2){	
								notice3(index, flag3);
								flag3 = 0;
							}
							front++;
						}
					}							
					arr.push(sum); //그래프 데이터 삽입.	
					sum = 0; //sum 갱신
				}				
			}
			
			//하이차트 그리기
			Highcharts.chart('container', {
				colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655',
					'#FFF263', '#6AF9C4'],
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

/*
 * 실시간 트래픽 관제 알고리즘
 * 
 * @Author : 조영호
 * 
 * 1. 4초에 한번씩 전체 트래픽 양을 검사한다.
 * 2. 당월 해당 날까지의 트래픽 양이 10k를 초과하면 경고 알람을 발생시킨다.
 * 3. 당월 해당 날까지의 트래픽 양이 100k를 초과하면 긴급  알람을 발생시킨다.
 * 
 */
function trafficTracking(){
	setInterval(function(){ 
		jQuery.ajax({
			type : "GET",
			url : "api/traffic/trafficData",
			dataType : "json",
			success : function(res){
				var totalTraffic = 0;	
				for (index = 0; index < res.trafficData.length; index++){ //전체트래픽을 구한다.
					totalTraffic += res.trafficData[index].trafficContentLength;
				}
				if (totalTraffic > 10000){
					if (totalTraffic > 100000){		
						notice1(flag1);
						flag1 = 0;
					}
					else{
						let flag = 1;
						notice2(flag2);
						flag2 = 0;
					}
				}
			},
			error : function(err){
				alert(err);
			}
		});
	}, 4000);
}

var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("closeNotice")[0];

function notice1(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Urgent");
	    $(".modal-body > p").html("<p>트래픽이 100k를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "crimson");
	    $(".modal-footer").css("background-color", "crimson");
	    goStaticGraphAjax();
	}
}
function notice2(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Warning");
	    $(".modal-body > p").html("<p>트래픽이 10k를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "sandybrown");
	    $(".modal-footer").css("background-color", "sandybrown");
	    goStaticGraphAjax();
	}
}
function notice3(index, flag){
	if (flag != 0){
		setTimeout(function(){
			modal.style.display = "block";
	        $(".modal-header > h2").html("Notice");
	        let revDate = eval(index);
	        let temp = new Date(revDate);
	        let noticeDate = temp + "";
	       
	        $(".modal-body > p").html("<p>" + noticeDate.substring(8, 10) + "일에, 트래픽이 비정상적으로 상승했습니다..</p>");
	        $(".modal-header").css("background-color", "darkorchid");
	        $(".modal-footer").css("background-color", "darkorchid");    	
		}, 7000);
	}
}
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}