<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/home.css?ver=7">
</head>

<div style="width:90%;margin-top:40px;margin-left:120px;">
	<!-- 1st -->
	<div id="wrap" class="container">
		<table class="table">
			<tr>
				<th colspan="5"><a href="board1" class="boardtitle">게시판1</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="1stMessage">
			</tbody>
		</table>		
	</div>
	<!-- 2nd -->
	<div id="wrap" class="container">
		<table class="table">
			<tr>
				<th colspan="5"><a href="board1" class="boardtitle">게시판2</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="2ndMessage">
			</tbody>
		</table>		
	</div>
	<!-- 3rd -->
	<div id="wrap" class="container">
		<table class="table">
			<tr>
				<th colspan="5"><a href="board1" class="boardtitle">게시판3</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="3rdMessage">
			</tbody>
		</table>
	</div>
	<!-- 4rd -->
	<div id="wrap" class="container">
		<table class="table">
			<tr>
				<th colspan="5"><a href="board1" class="boardtitle">게시판4</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="4rdMessage">
			</tbody>
		</table>
	</div>
	
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script>
$(document).ready(function(){
	jQuery.ajax({
		type: "GET",
		url: "homelist",
		dataType: "json",
		data: "",
		success: function(res){
			//Draw Table Data
				//1st Message
				for(var i=0; i<res.messagelist1.length; i++)
					$("#1stMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist1[i].message_num+")\" class=\"boardtds\">"+res.messagelist1[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist1[i].message_date.substring(0,10)+"</td></tr>");
				//2nd Message
				for(var i=0; i<res.messagelist2.length; i++)
					$("#2ndMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist2[i].message_num+")\" class=\"boardtds\">"+res.messagelist2[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist2[i].message_date.substring(0,10)+"</td></tr>");				
				//3rd Message
				for(var i=0; i<res.messagelist3.length; i++)
					$("#3rdMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist3[i].message_num+")\" class=\"boardtds\">"+res.messagelist3[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist3[i].message_date.substring(0,10)+"</td></tr>");
				//4rd Message
				for(var i=0; i<res.messagelist4.length; i++)
					$("#4rdMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist4[i].message_num+")\" class=\"boardtds\">"+res.messagelist4[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist4[i].message_date.substring(0,10)+"</td></tr>");
		},
		error: function(err){
			alert("err");
		}
	});
});
</script>
<script>
function goRead(message_num){
	location.href= 'read?'+ "message_num=" + message_num;
}
</script>