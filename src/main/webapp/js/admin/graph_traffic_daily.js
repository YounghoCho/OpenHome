/*
 * 하루 단위로, 5가지 Level별 트래픽의 합을 구하여 그래프를 그린다.
 * 
 * @Author : 조영호
 */
var allTraffic, readTraffic, writeTraffic, uploadTraffic, downloadTraffic;

function drawDailyTrafficGraph(){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".homeReadDiv").hide();
	$(".apiGraphDiv").hide();
	$("#container2").hide();
	$(".staticGraphDiv").show();
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
		success : function(res){

			let length = res.trafficCount;
			let startDate = Date.parse(res.trafficData[0].trafficDate.substring(0,10));
			let endDate = Date.parse(res.trafficData[length-1].trafficDate.substring(0,10));
			var queue = new Array();
			var unusual = new Array();
			var front = 0, rear = 0, interval = 3;	
			
			sumDailyTraffic(allTraffics, "totalTraffic");
			sumDailyTraffic(readTraffics, "read");
			sumDailyTraffic(writeTraffics, "write");
			sumDailyTraffic(uploadTraffics, "fileUpload");
			sumDailyTraffic(downloadTraffics, "fileDownload");
			
			/*
			 * 외부 For문 : 하루 단위로 반복한다.
			 * 내부 For문 : 각 트래픽별로 반복한다.
			 * if문 : 트래픽의 합을 구한다.
			 * else문 : 날짜가 변하는 시점에서, 현재 트래픽의 index를 기억시킨 뒤 내부 For문을 종료한다. 
			 */
			function sumDailyTraffic(arr, str){
				let sum = 0, recentIndex = 0;		
				
				for (let index = startDate; index <= endDate; index += 86400000){
					for (i = recentIndex; i < length; i++){						
						if (Date.parse(res.trafficData[i].trafficDate.substring(0,10)) == index){
								if (str == "totalTraffic" || res.trafficData[i].trafficKind == str){
									sum += res.trafficData[i].trafficContentLength;								
								}				
						}else {
							recentIndex = i;
							break;
						}
					}
					//비 정상 트래픽을 검사한다.
					testUnusualTraffic(sum, index, str);
					arr.push(sum);
					sum = 0;
				}
				//트래픽 합계를 모두 구한 뒤, 비정상 트래픽 알람을 발생시킨다.
				if(unusual.length != 0){
					notice3(unusual, flag3);
					flag3 = 0;
				}
			}
			
			/*
			 * 비 정상 트래픽 검사
			 * 
			 * 특정한 날에 발생한 트래픽이 일정한 기간(7일)동안 발생한 평균 트래픽보다 4배가 많으면, 비정상 트래픽으로 간주한다.
			 * 최초의 일주일(Interval = 7)은 EnQueue만 발생한다.
			 * 일주일이 되는 시점부터 비정상 트래픽을 검사하고, Dequeue, Enqueue를 반복한다.
			 */		
			function testUnusualTraffic(sum, index, str){
				if (str == "totalTraffic"){
					if (rear < (interval + front)){
						queue.push(sum); //Enqueue
						rear++;
					}else {
						let temp = 0;
						for (let i = front; i < rear; i++){
							temp += queue[i];
						}
						if (sum > (temp / interval) * 4){
							unusual.push(index);
						}
						front++; //Dequeue
						queue.push(sum);
						rear++;
					}
				}	
			}
			
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
			});
	
			//월별(Monthly) 그래프 구현에 필요한 데이터를 사전에 계산하여, 향후 반복 계산을 최소화한다.
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
		},//End success
		error: function(err){
			alert("drawDailyTrafficGraph error : " + err);
		}
	});
	location.hash = '/#/page:graph';
}