<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?ver=27">
</head>

<!-- header -->
<div id="header" style="background-color:#000040;">
		<a href="board0" style="width:50px;"><img src="${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;"/></a>
			<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;">OPENWORKS Admin</font>
</div>

<!-- menu -->
<div id="menu">
<ul class="menudecoration">
	<li><a href="javascript:goAdmin(0)">게시판 관리</a></li>
	<li><a href="javascript:goAdmin(1)">게시글 관리</a></li>
	<li><a href="javascript:goAdmin(2)">트래픽 통계</a></li>
</ul>
</div>
<script>
function goAdmin(index){
	location.href=""+index;
}
</script>

<!-- top -->
<div id="top">
	Welcome to OpenWorks
</div>

<!-- body-home -->
<div style="margin:10px;" class="homeMainDiv">
	<!-- 1st -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(1,1)">게시판1</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="1stMessage">
			</tbody>
		</table>		
	</div>
	<!-- 2nd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(2,1)">게시판2</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="2ndMessage">
			</tbody>
		</table>		
	</div>
	<!-- 3rd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(3,1)">게시판3</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="3rdMessage">
			</tbody>
		</table>
	</div>
	<!-- 4rd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(4,1)">게시판4</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="4rdMessage">
			</tbody>
		</table>
	</div>
</div>

<!-- body-board -->
<div id="singleBoard">
	<div id="wrap" class="container board">	<!--auto margin-->
		<div class="boardtitle tt"></div>

		<table class="table">
			<thead>
			<tr>
				<th style="width:5%">번호</th>
				<th style="width:35%;">제목</th>
				<th style="width:40%">미리보기</th>
				<th style="width:10%">작성날짜</th>
				<th style="width:10%">작성자</th>
			</tr>
			</thead>
			<tbody class="tbody">	
			</tbody>
		</table>
		
		<a type="button" class="btn btn-success pull-right" style="margin-right:20px">글쓰기</a><br>

		<div class="text-center">
			<ul class="pagination">
					 <li id="indexNow"></li>
			   		 <li id="indexOthers"></li>
			</ul>
		</div>	
	
	</div>
</div>

<!-- body-read -->
<div class="homeReadDiv">
	<div id="wrap" class="container read">	<!--auto margin-->
	
		<table class="table" style="height:400px;">			
			<tbody>
			<tr>
				<td style="height:50px" id="boardTdSubject"></td>
			</tr>
			<tr>
				<td style="height:100px">첨부파일 영역</td>
			</tr>
			<tr>
				<td style="height:250px;border-bottom:1px solid #ddd;" id="boardTdContent"></td>
			</tr>
			</tbody>
		</table>

		<a type="button" class="btn btn-default pull-right" style="margin-right:10px;width:80px;">취소</a>		
		<a type="button" class="btn btn-success pull-right" style="margin-right:20px;width:80px;">글쓰기</a>

	</div>	
</div>

<!-- static Graph -->
<div class="staticGraphDiv">
	<div id="wrap" class="container static">
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
		<div id="container"></div>
		
		<script>
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
		</script>
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js?ver=11"></script>
<script>
///// locate Page and active Ajax 
if(${boardNumberInt}==0){
	$(".homeMainDiv").html("게시판 관리 페이지 입니다 000");
	//goHomeAjax();		  
}
if(${boardNumberInt}==1){
	$(".homeMainDiv").html("게시글 관리 페이지 입니다 111");
	//goBoardAjax(${boardNumberInt}, ${currentPageNo});
}
if(${boardNumberInt}==2){
	staticGraphAjax();
}
</script>