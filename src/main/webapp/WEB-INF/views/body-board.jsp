<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
</head>

<div>
	<div id="wrap" class="container">	<!--auto margin-->
		<table class="table">
			<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${messagelist}" var="messageDTO">
				<tr>
					<td>${messageDTO.message_num}</td>
					<td>${messageDTO.board_num}</td>
					<td>${messageDTO.message_subject}</td>
					<td>${messageDTO.message_content}</td>
					<td>${messageDTO.message_date}</td>
					<td>${messageDTO.message_writer}</td>
					<td>${messageDTO.message_pwd}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>2</td>
				<td>영준</td>
				<td>동생아</td>
				<td>우리는</td>
				<td>배그충</td>
			</tr>
			</tbody>
		</table>
		
		<a type="button" class="btn btn-success pull-right">글쓰기</a>
		<div class="text-center">
			<ul class="pagination">
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
			</ul>
		</div>
	</div>
	
</div>

<!-- 브라우저가 스크립트를 늦게 읽게 해서 로딩속도를 올린다. -->
<!-- jquery 로드 후 부트스트랩 js 불러온다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>

