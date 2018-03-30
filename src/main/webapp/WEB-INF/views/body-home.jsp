<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<head>
	<!-- 캐시를 제거해서 실시간으로 css를 반영한다 -->
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/home.css?ver=4">
</head>


<div>
	<!-- 1st -->
	<div id="wrap" class="container">	<!--auto margin-->
		<table class="table">
			<thead>
			<tr>
				<th><a href="board1" class="boardtitle">게시판1</a></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${messagelist}" var="messageDTO" begin="1" end="5" step="1">
				<tr>
					<td><a href="read" class="boardtds">${messageDTO.message_sample}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 2nd -->
	<div id="wrap" class="container" id="boards">
		<table class="table">
			<thead>
			<tr>
				<th><a href="board2" class="boardtitle">게시판2</a></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${messagelist2}" var="messageDTO" begin="1" end="5" step="1">
				<tr>
					<td><a href="read" class="boardtds">${messageDTO.message_sample}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>		
	</div>
	
	<!-- 3rd -->
	<div id="wrap" class="container" id="boards">
		<table class="table">
			<thead>
			<tr>
				<th><a href="board3" class="boardtitle">게시판3</a></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${messagelist3}" var="messageDTO" begin="1" end="5" step="1">
				<tr>
					<td><a href="read" class="boardtds">${messageDTO.message_sample}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 4rd -->
	<div id="wrap" class="container" id="boards">
		<table class="table">
			<thead>
			<tr>
				<th><a href="board4" class="boardtitle">게시판4</a></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${messagelist4}" var="messageDTO" begin="1" end="5" step="1">
				<tr>
					<td><a href="read" class="boardtds">${messageDTO.message_sample}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>

<!-- 브라우저가 스크립트를 늦게 읽게 해서 로딩속도를 올린다. -->
<!-- jquery 로드 후 부트스트랩 js 불러온다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>