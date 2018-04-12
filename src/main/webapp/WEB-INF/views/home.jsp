<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/layout.css?ver=26">
</head>

<!-- header -->
<div id="header" style="background-color:#000040;">
		<a onclick="javascript:goHomeAjax()" style="width:50px;"><img src="../${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;cursor:pointer;"/>
			<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;cursor:pointer;">OPENHOME</font></a>
</div>

<!-- menu -->
<div id="menu">
<ul class="menudecoration">
	<li style="cursor:pointer;"onclick="javascript:goHomeAjax()">홈</li>
	<li style="cursor:pointer;"onclick="goBoardAjax(1, 1)">게시판1</li>
	<li style="cursor:pointer;"onclick="goBoardAjax(2, 1)">게시판2</li>
	<li style="cursor:pointer;"onclick="goBoardAjax(3, 1)">게시판3</li>
	<li style="cursor:pointer;"onclick="goBoardAjax(4, 1)">게시판4</li>
</ul>
</div>

<!-- top -->
<div id="top">
	Welcome to OPENHOME
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
			<tbody id="4thMessage">
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home.js?ver=8"></script>
