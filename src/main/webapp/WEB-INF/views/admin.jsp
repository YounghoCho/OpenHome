<%@ page session="true" %> <!-- 세션 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String id = (String)session.getAttribute("userLoginInfo");     
if (id == null || id.equals("")) {                    
response.sendRedirect("/OpenHome/login");    
}
%>

<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css?ver=3">
</head>


<!-- 게시판 순서 조절 팝업 -->
<div class="orderWindow">
	<button class="btn btn-default pull-right" id="closeChange" style="margin:10px;"><font style="font-weight:bold;">x</font></button>
	<div style="width:80%;height:80%;margin-left:50px;margin-top:60px;text-align:center;">	
		<ul id="sortable">
		</ul>
	</div>
</div>
<!-- 새로운 게시판 추가 팝업 -->
<div class="boardTitleWindow">
	<button class="btn btn-default pull-right" id="closeChange2" style="margin:10px;"><font style="font-weight:bold;">x</font></button>
	<div style="width:80%;height:40%;margin-left:50px;margin-top:60px;text-align:center;">	
		<input id="newTitle" type="text" placeholder="새로운 게시판명을 입력해 주세요" style="width:280px;height:38px;padding-left:5px;"/>
		<a id="updateBoardButton" type="button" class="btn btn-success" style="margin-right:20px;padding:8px;">수정</a>
	</div>
</div>
<!-- 트래픽 알림 팝업 -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="closeNotice">&times;</span>
      <h2></h2>
    </div>
    <div class="modal-body">
    	<p style="font-weight:bold;margin:0;padding:5px;"></p>
    </div>
    <div class="modal-footer">
      <h3>OPENHOME</h3>
    </div>
  </div>
</div>

<!-- header -->
<div id="header" style="background-color:seagreen;">
		<a href="${pageContext.request.contextPath}/admin" style="width:50px;"><img src="${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;"/></a>
			<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;">OPENHOME Admin</font>
</div>
<div id="logOutButton" style="position:absolute; top:0; right:0; color:white; font-weight:bold; margin:15px; cursor:pointer; display:none;">
	로그아웃
</div>

<div id="center">
<!-- menu -->
<div id="menu">
<ul class="menudecoration">
	<li style="cursor:pointer;"><a onclick="javascript:goBoardManageAjax()">게시판 관리</a></li>
	<li style="cursor:pointer;"><a onclick="javascript:goArticlesAjax(1)">게시글 관리</a></li>
	<li style="cursor:pointer;"><a onclick="javasciprt:drawDailyTrafficGraph()">트래픽 통계</a></li>
	<li style="cursor:pointer;"><a onclick="javasciprt:goApiGraphAjax()">API 사용량</a></li>	
</ul>
</div>

<div id="center-right">
<!-- top -->
<div id="top">
	Welcome to OPENHOME
</div>

<div id="body">

<!-- body-home -->
<div style="margin:10px;" class="homeMainDiv">

	<div style="width:100%;height:50px;">
		<input id="boardTitle" type="text" placeholder="게시판 명을 입력하세요" style="width:280px;height:38px;padding-left:5px;"/>
		<a id="newBoardButton" type="button" class="btn btn-success" style="margin-right:20px;padding:8px;">추가</a>
		<a id="orderButton" type="button" class="btn btn-success pull-right" style="margin-right:20px;padding:8px;">순서 조정</a>
	</div>
	
	<table class="table">
		<thead>
		<tr>
			<th style="width:85%">게시판목록</th>
			<th style="width:5%"></th>
			<th style="width:5%"></th>
			<th style="width:5%"></th>
		</tr>
		</thead>
		<tbody class="tbody admin">	
		</tbody>
	</table>

</div>

<!-- body-board -->
<div id="singleBoard">
	<div id="wrap" class="container board">	<!--auto margin-->
		<div class="boardtitle tt"></div>

		<table class="table">
			<thead>
			<tr>
				<th style="width:10%"></th>
				<th style="width:60%">게시글 목록</th>
				<th style="width:20%;">게시판 이름</th>
				<th style="width:10%"></th>
			</tr>
			</thead>
			<tbody class="tbody">	
			</tbody>
		</table>
		
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

	</div>	
</div>

<!-- static Graph -->
<div class="staticGraphDiv">
	<div id="wrap" class="container static">
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		
		<a id="dailyTraffic" type="button" class="btn btn-success" style="margin-top:13px;margin-right:10px;padding:8px;">일일 그래프</a>
		<a id="monthlyTraffic" type="button" class="btn btn-success" style="margin-top:13px;padding:8px;">월간 그래프</a>
		<div id="container"  style="margin-top:20px"></div>	
		<div id="container2" style="margin-top:20px"></div>
	</div>
</div>

<div class="apiGraphDiv">
	<a id="dailyApi" type="button" class="btn btn-success" style="margin-top:13px;margin-left:13px;margin-right:10px;padding:8px;">일일 그래프</a>
	<a id="monthlyApi" type="button" class="btn btn-success" style="margin-top:13px;padding:8px;">월간 그래프</a>
	<div id="BubbleChartHead" style="margin-top:50px;padding:20px;font-weight:bold;"><p style="font-size:18px;text-align:center;margin-right:8%;">일일별 API 사용량</p></div>
	<div id="BubbleChart"></div>
	<div id="DonutChartHead" style="margin-top:50px;padding:20px;font-weight:bold"><p style="font-size:18px;text-align:center;margin-right:10%;">5월 API 사용량</p></div>
	<div id="DonutChart"></div>
</div>
</div>
</div>
</div>
<!-- footer -->
<div id="footer">
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/admin.js?ver=2"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/common_admin.js?ver=3"></script>

<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/billboard.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/billboard.css?ver=1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/graph_notice.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/graph_traffic_daily.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/graph_traffic_monthly.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/graph_api.js?ver=3"></script>  
<script>
var LoginCheck = "<%=session.getAttribute("userLoginInfo")%>";
//세션이 유효하면
if(LoginCheck != null && LoginCheck != "null"){
	$("#logOutButton").show();
	trafficTracking(); //트래픽 감지 시작
}
else{
	loginPane();
}
</script>