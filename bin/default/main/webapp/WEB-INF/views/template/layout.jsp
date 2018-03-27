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
	<tiles:insertAttribute name="header"/>
</header>
<menu id="menu">
	<tiles:insertAttribute name="menu"/>
</menu>
<section id="top">
	<tiles:insertAttribute name="top"/>
</section>
<section id="body">
	<tiles:insertAttribute name="body"/>
</section>
<footer id="footer">
	<tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>