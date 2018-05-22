<%@ page session="true" %> <!-- 세션 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=1">
<%-- <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css?ver=3"> --%>
</head>
<body>
<div id="mask"></div>
<!-- 로그인 팝업-->
<div class="window">
	<div style="width:80%;height:80%;margin-left:50px;margin-top:60px;text-align:center;">
		<img src="${pageContext.request.contextPath}/image/logo.png" style="width:65px;margin:5px;"/><br/>
		<input type="text" id="managerId" name="username" placeholder="ID" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<input type="password" id="managerPwd" name="password" placeholder="PASSWORD" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<a type="button" class="btn btn-success" style="margin:5px;width:280px;height:50px;" onclick="javascript:loginAjax()">
					<div style="margin-top:10px;">로그인</div></a>
	</div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/admin.js?ver=2"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/common_admin.js?ver=2"></script>
</html>