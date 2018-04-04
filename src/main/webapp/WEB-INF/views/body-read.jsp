<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=1">
</head>

<div>
	<div id="wrap" class="container">	<!--auto margin-->

		<table class="table" style="height:400px;">
			<thead>
				<tr>
					<th id="boardTh">게시판1</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<td style="height:50px" id="boardTdSubject">ㅌ</td>
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

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script>
$(document).ready(function(){
	jQuery.ajax({
		type: "GET",
		url: "readContents",
		dataType: 'json',
		data: 'message_num='+ ${param.message_num},
		success: function(res){

			//alert(res.originalMessageInfo[0].message_num);
			$("#boardTh").html("게시판1");
			$("#boardTdSubject").html(res.originalMessageInfo[0].message_subject);
			$("#boardTdContent").html(res.originalMessageInfo[0].message_content);
		},
		error: function(err){
			alert("lose:"+err.status);
		}
	});
});
</script>
