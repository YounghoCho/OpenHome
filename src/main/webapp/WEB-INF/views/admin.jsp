<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css?ver=1">
</head>

<div id="mask"></div>
<!-- Login -->
<div class="window">
	<div style="width:80%;height:80%;margin-left:50px;margin-top:60px;text-align:center;">
		<img src="${pageContext.request.contextPath}/image/logo.png" style="width:65px;margin:5px;"/><br/>
		<input type="text" id="managerId" placeholder="ID" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<input type="password" id="managerPwd" placeholder="PASSWORD" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<a type="button" class="btn btn-success" style="margin:5px;width:280px;height:50px;" onclick="javascript:loginAjax()">
			<div style="margin-top:10px;">로그인</div>
		</a>
	</div>
</div>
<!-- Update Board List -->
<div class="orderWindow">
	<button class="btn btn-default pull-right" id="closeChange" style="margin:10px;"><font style="font-weight:bold;">x</font></button>
	<div style="width:80%;height:80%;margin-left:50px;margin-top:60px;text-align:center;">	
		<ul id="sortable">
		</ul>
	</div>
</div>
<!-- Update Board Title -->
<div class="boardTitleWindow">
	<button class="btn btn-default pull-right" id="closeChange2" style="margin:10px;"><font style="font-weight:bold;">x</font></button>
	<div style="width:80%;height:40%;margin-left:50px;margin-top:60px;text-align:center;">	
		<input id="newTitle" type="text" placeholder="새로운 게시판명을 입력해 주세요" style="width:280px;height:38px;padding-left:5px;"/>
		<a id="updateBoardButton" type="button" class="btn btn-success" style="margin-right:20px;padding:8px;">수정</a>
	</div>
</div>


<!-- header -->
<div id="header" style="background-color:seagreen;">
		<a href="${pageContext.request.contextPath}/admin" style="width:50px;"><img src="${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;"/></a>
			<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;">OPENHOME Admin</font>
</div>

<div id="center">
<!-- menu -->
<div id="menu">
<ul class="menudecoration">
	<li style="cursor:pointer;"><a onclick="javascript:goBoardManageAjax()">게시판 관리</a></li>
	<li style="cursor:pointer;"><a onclick="javascript:goArticlesAjax()">게시글 관리</a></li>
	<li style="cursor:pointer;"><a onclick="javasciprt:goStaticGraphAjax()">트래픽 통계</a></li>
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
			<th style="width:85%">게시글판목록</th>
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
	
		<div id="container"></div>
		
	</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js?ver = 1"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/graph.js?ver = 2"></script>