<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" type="text/css" href="css/layout.css">
</head>
<body>
<header id="header">
	<tiles:insertAttribute name="header" ignore="true"/> <!-- ignore: 값이 없더라도 error페이지 대신 페이지가 빈페이지가 나오게해준다. -->
</header>
<menu id="menu">
	<tiles:insertAttribute name="menu" ignore="true"/>
</menu>
<section id="top">
	<tiles:insertAttribute name="top" ignore="true"/>
</section>
<section id="body">
	<tiles:insertAttribute name="body" ignore="true"/>
</section>
<footer id="footer">
	<tiles:insertAttribute name="footer" ignore="true"/>
</footer>
</body>
</html>