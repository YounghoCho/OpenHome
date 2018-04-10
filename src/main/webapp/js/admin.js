function staticGraphAjax(){
	$(".container.static").show();
	var traffics = new Array();
	
	jQuery.ajax({
		type: "GET",
		url: "api/getTrafficList",
		dataType: "json",
		data: "",
		
		success: function(res){
			var index = res.resultTraffic[0].traffic_num-1;
			var length = res.resultTrafficCount[0].countAll;
			for(var i = index ; i < length ; i++) {
				traffics[i] = res.resultTraffic[i].traffic_content_length;
				//alert(i+" : "+traffics[0]+" "+traffics[1]);
			}	
			//Graph
			Highcharts.chart('container', {
			    title: {
			        text: 'OPENWORKS 트래픽 그래프'
			    },
			    subtitle: {
			        text: '트래픽 그래프는 과금 정책과 직접적인 관련이 있습니다.'
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
			            pointStart: 1
			        }
			    },
				credits:{enabled: false},			
			    series: [{
			        name: '전체 트래픽',
			        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]	
			    }, {
			        name: '페이지 로딩',
			        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
			    }, {
			        name: '파일 업로드',
			        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
			    }, {
			        name: '파일 다운로드',
			        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
			    }, {
			    	name: '테스트 트래픽',
			    	data: traffics
			    }],
			
			    responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 500
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
	
}