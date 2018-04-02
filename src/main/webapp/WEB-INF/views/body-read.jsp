<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=1">
</head>

<div>
	<div id="wrap" class="container">	<!--auto margin-->
		<table class="table" style="height:400px;">
			<thead>
				<tr>
					
					<c:forEach items="${originalMessageInfo}" var="whichBoard">
						<c:if test="${whichBoard.board_num eq 1}">
							<th>게시판1</th>
						</c:if>
						<c:if test="${whichBoard.board_num eq 2}">
							<th>게시판2</th>
						</c:if>
						<c:if test="${whichBoard.board_num eq 3}">
							<th>게시판3</th>
						</c:if>
						<c:if test="${whichBoard.board_num eq 4}">
							<th>게시판4</th>
						</c:if>
					</c:forEach>
					
				</tr>
			</thead>
			
			<c:forEach items="${originalMessageInfo}" var="originalMessageInfo">	<!-- how to get ajax? forEach를 써야만 List에서 데이터를 꺼내쓸 수 있다.. -->
			<tbody>
			<tr>
				<td style="height:50px">${originalMessageInfo.message_subject}</td>
			</tr>
			<tr>
				<td style="height:100px">첨부파일 영역</td>
			</tr>
			<tr>
				<td style="height:250px;border-bottom:1px solid #ddd;">${originalMessageInfo.message_content}</td>
			</tr>
			</tbody>
			</c:forEach>
		</table>
		<a type="button" class="btn btn-default pull-right" style="margin-right:10px;width:80px;">취소</a>		
		<a type="button" class="btn btn-success pull-right" style="margin-right:20px;width:80px;">글쓰기</a>

	</div>
	
</div>

<!-- 브라우저가 스크립트를 늦게 읽게 해서 로딩속도를 올린다. -->
<!-- jquery 로드 후 부트스트랩 js 불러온다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>

