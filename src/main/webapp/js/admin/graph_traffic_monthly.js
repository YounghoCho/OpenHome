//월별 트래픽 통계 그래프
function drawMonthlyTrafficGraph(){
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