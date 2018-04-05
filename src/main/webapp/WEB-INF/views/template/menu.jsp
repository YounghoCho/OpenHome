
<head>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/menu.css?ver=2">
</head>
<div>
<ul class="menudecoration">
	<li><a href="javascript:goBoard(0)">홈</a></li>
	<li><a href="javascript:goBoard(1)">게시판1</a></li>
	<li><a href="javascript:goBoard(2)">게시판2</a></li>
	<li><a href="javascript:goBoard(3)">게시판3</a></li>
	<li><a href="javascript:goBoard(4)">게시판4</a></li>
</ul>
</div>
<script>
function goBoard(index){
	location.href="board"+index;
}
</script>