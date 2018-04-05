<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" type="text/css" href="css/layout.css?ver=3">
</head>
<body>

<div id="wrap">
	<div id="header">
		<tiles:insertAttribute name="header" ignore="true"/> <!-- ignore: 값이 없더라도 error페이지 대신 페이지가 빈페이지가 나오게해준다. -->
	</div>
	<div id="center">
		<div id="menu">
			<tiles:insertAttribute name="menu" ignore="true"/>
		</div>
		<div id="center-right">
			<div id="top">
				<tiles:insertAttribute name="top" ignore="true"/>
			</div>
			<div id="body">
				<tiles:insertAttribute name="body" ignore="true"/>
			</div>
		</div>
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" ignore="true"/>
	</div>
</div>

</body>
</html>