//트래픽 알림이 1번만 동작하게 한다
var flag1 = 1;
var flag2 = 1;
var flag3 = 1;

let allTraffic, readTraffic, writeTraffic, uploadTraffic, downloadTraffic;
//그래프 그리기.
function goStaticGraphAjax(){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".homeReadDiv").hide();
	$(".apiGraphDiv").hide();
	$(".staticGraphDiv").show();
	$("#container2").hide();
	$("#container").show();	
	
	var allTraffics = new Array();
	var readTraffics = new Array();
	var writeTraffics = new Array();
	var uploadTraffics = new Array();
	var downloadTraffics = new Array();
	
	$.ajax({
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
			graphAlgorithm(uploadTraffics, "fileUpload");
			graphAlgorithm(downloadTraffics, "fileDownload");
			
			function graphAlgorithm(arr, str){
				var sum = 0;
				var memo = 0;
				var queue = new Array();
				var unusual = new Array();
				var front = 0, rear = 0, interval = 7;				
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
						//alert("rear:"+rear+", front:"+front);
						//alert("sum="+sum);
						if (rear < (interval + front)){
							queue.push(sum);
							rear++;
						}else {
							//3개가 찼을때 비교하는 알고리즘
							let temp = 0;
							for (let i = front; i < rear; i++){
								temp += queue[i];
							}
							//alert("sum > (temp/interval)*2 : " + sum + "/ (" + temp + "/3*2)");
							if (sum > (temp / interval) * 4){
								unusual.push(index);
							}
							++front;
							queue.push(sum);
							rear++;
						}
					}							
					arr.push(sum); //그래프 데이터 삽입.	
					sum = 0; //sum 갱신
				}
				//모든 날짜를 순환한 후 비정상 날짜들을 알린다.
				if(unusual.length != 0){
					notice3(unusual, flag3);
					flag3 = 0;
				}
			}
			
			//하이차트 그리기
			Highcharts.chart('container', {
				colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655',
					'#FFF263', '#6AF9C4'],
			    title: {
			        text: '일일별 트래픽 사용량'
			    },
			    chart: {
			        width: 1400
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
			            text: 'Traffic(Byte)'
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
			            pointStart: parseInt(dateFormating(startDate))
			        }
			    },
				credits:{enabled: false},			
			    series: [{
			        name: '전체 트래픽',
			        data: allTraffics	
			    }, {
			        name: '게시글 읽기',
			        data: readTraffics
			    }, {
			        name: '게시글 쓰기',
			        data: writeTraffics
			    }, {
			        name: '파일 업로드',
			        data: uploadTraffics
			    }, {
			        name: '파일 다운로드',
			        data: downloadTraffics
			    }],
			
			    responsive: {
			        rules: [{
			            condition: {
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
			});//end Traffic Graph
	
			//트래픽 총 합 구하기
			allTraffic = sumMonthTraffic(allTraffics);
			readTraffic = sumMonthTraffic(readTraffics);
			writeTraffic = sumMonthTraffic(writeTraffics);
			uploadTraffic = sumMonthTraffic(uploadTraffics);
			downloadTraffic = sumMonthTraffic(downloadTraffics);		
			
			function sumMonthTraffic(arr){
				let sum = 0;
				for(let i = 0; i < arr.length; i++)
					sum += arr[i];
				return sum;
			}			
		},//end success
		error: function(err){
			alert("e"+err);
		}
	});
	location.hash = '/#/page:graph';
}

//월별 트래픽 통계 그래프
function goStaticGraphAjax2(){
	$("#container").hide();
	$("#container2").show();	
	
	Highcharts.chart('container2', {
	    chart: {
	        type: 'bar',
	        width: 1400
	    },
	    title: {
	        text: '5월 트래픽 통계'
	    },
	    xAxis: {
	        categories: ['5월 트래픽'],
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '트래픽 사용량 (Byte)',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' millions'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'bottom',
	        x: -40,
	        y: -80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '전체 트래픽',
	        data: [allTraffic]
	    }, {
	        name: '게시글 읽기',
	        data: [readTraffic]
	    }, {
	        name: '게시글 쓰기',
	        data: [writeTraffic]
	    }, {
	        name: '파일 업로드',
	        data: [uploadTraffic]
	    }, {
	        name: '파일 다운로드',
	        data: [downloadTraffic]
	    }]
	});
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
		$.ajax({
			type : "GET",
			url : "api/traffic/trafficData",
			dataType : "json",
			success : function(res){
				var totalTraffic = 0;	
				for (index = 0; index < res.trafficData.length; index++){ //전체트래픽을 구한다.
					totalTraffic += res.trafficData[index].trafficContentLength;
				}
				if (totalTraffic < 0){
					if (totalTraffic < 0){		
						notice1(flag1);
						//컨텐트를 누르면 flag를 바꾼다.
						flag1 = 0;
						flag2 = 1;

					}
					else{
						let flag = 1;
						notice2(flag2);
						flag2 = 0;
						flag1 = 1;
					}
				}
			},
			error : function(err){
				alert(err);
			}
		});
	}, 5000);
}

var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("closeNotice")[0];

function notice1(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Urgent");
	    $(".modal-body > p").html("<p>트래픽이 1000k를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "crimson");
	    $(".modal-footer").css("background-color", "crimson");
	}
}
function notice2(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Warning");
	    $(".modal-body > p").html("<p>트래픽이 100k를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "sandybrown");
	    $(".modal-footer").css("background-color", "sandybrown");
	}
}
function notice3(arr, flag){
	if (flag != 0){
		setTimeout(function(){
			modal.style.display = "block";
	        $(".modal-header > h2").html("Notice");
	        
	        var revDate, temp, noticeDate;
	        var result = "";
	        //alert(arr[0] + "," + arr[1]+","+arr[2]);
	        for(let index = 0; index < arr.length; index++){	        	
	        	revDate = (eval(arr[index]));
	        	temp = new Date(revDate);
	        	noticeDate = temp + "";
	        	result += noticeDate.substring(8, 10) + "일, ";
	        }
	 	       
	        $(".modal-body > p").html("<p>" + result + "트래픽이 비정상적으로 상승했습니다.</p>");
	        $(".modal-header").css("background-color", "darkorchid");
	        $(".modal-footer").css("background-color", "darkorchid");    	
		}, 5000);
	}
}
$(".modal-content").on("click", function(){
	goStaticGraphAjax();
	modal.style.display = "none";
});
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}