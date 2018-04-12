<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?ver=1">
</head>

<!-- login -->
<div id="mask"></div>
<div class="window">
	<div style="width:80%;height:80%;margin-left:50px;margin-top:60px;text-align:center;">
		<img src="${pageContext.request.contextPath}/image/logo.png" style="width:65px;margin:5px;"/><br/>
		<input type="text" id="managerId" placeholder="ID" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<input type="password" id="managerPwd" placeholder="PASSWORD" style="width:280px;height:50px;margin:5px;padding-left:5px;"/><br/>
		<a type="button" class="btn btn-success" style="margin:5px;width:280px;height:50px;" onclick="javascript:loginAjax()">
			<div style="margin-top:10px;">로그인</div>
		</a>
	</div>
</div>


<!-- header -->
<div id="header" style="background-color:#000040;">
		<a href="${pageContext.request.contextPath}/admin" style="width:50px;"><img src="${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;"/></a>
			<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;">OPENHOME Admin</font>
</div>

<!-- menu -->
<div id="menu">
<ul class="menudecoration">
	<li style="cursor:pointer;"><a onclick="">게시판 관리</a></li>
	<li style="cursor:pointer;"><a onclick="">게시글 관리</a></li>
	<li style="cursor:pointer;"><a onclick="javasciprt:staticGraphAjax()">트래픽 통계</a></li>
</ul>
</div>

<!-- top -->
<div id="top">
	Welcome to OPENHOME
</div>

<!-- body-home -->
<div style="margin:10px;" class="homeMainDiv">
	<!-- 1st -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(1,1)">게시판1</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="1stMessage">
			</tbody>
		</table>		
	</div>
	<!-- 2nd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(2,1)">게시판2</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="2ndMessage">
			</tbody>
		</table>		
	</div>
	<!-- 3rd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(3,1)">게시판3</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="3rdMessage">
			</tbody>
		</table>
	</div>
	<!-- 4rd -->
	<div class="container home">
		<table class="table">
			<tr>
				<th colspan="5"><a class="boardtitle" href="javascript:goBoardAjax(4,1)">게시판4</a></th>
				<th>작성날짜</th>
			</tr>
			<tbody id="4rdMessage">
			</tbody>
		</table>
	</div>
</div>

<!-- body-board -->
<div id="singleBoard">
	<div id="wrap" class="container board">	<!--auto margin-->
		<div class="boardtitle tt"></div>

		<table class="table">
			<thead>
			<tr>
				<th style="width:5%">번호</th>
				<th style="width:35%;">제목</th>
				<th style="width:40%">미리보기</th>
				<th style="width:10%">작성날짜</th>
				<th style="width:10%">작성자</th>
			</tr>
			</thead>
			<tbody class="tbody">	
			</tbody>
		</table>
		
		<a type="button" class="btn btn-success pull-right" style="margin-right:20px">글쓰기</a><br>

		<div class="text-center">
			<ul class="pagination">
					 <li id="indexNow"></li>
			   		 <li id="indexOthers"></li>
			</ul>
		</div>	
	
	</div>
</div>

<!-- body-read -->
<div class="homeReadDiv">
	<div id="wrap" class="container read">	<!--auto margin-->
	
		<table class="table" style="height:400px;">			
			<tbody>
			<tr>
				<td style="height:50px" id="boardTdSubject"></td>
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

<!-- static Graph -->
<div class="staticGraphDiv">
	<div id="wrap" class="container static">
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
		<div id="container"></div>
		
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js?ver=18"></script>
<script>
$(document).ready(function(){
	//세션정보없으면 검은막 띄우기(show())
	
	//검은막 띄우기
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();
	$("#mask").css({'width':maskWidth, 'height':maskHeight});
	$("#mask").fadeIn(1000);
	$("#mask").fadeTo("slow", 0.9);
		$('.window').fadeIn(1000);

	//검은막 숨기기
	//화면 클릭했을때가 아니라 버튼 눌러서 로그인되면 지워지게 해야함.
	$('.window .close').click(function () {  
	    $('#mask, .window').hide();  
	});       	
	$('#mask').click(function () {  
	    $(this).hide();  
	    $('.window').hide();  
	});
});

function loginAjax(){
		var id = $("#managerId").val();
		var pwd = $("#managerPwd").val();
		jQuery.ajax({
			type : "GET",
			url : "api/adminLogin",
			dataType : "json",	//count로 1개인지 확인
			data : "managerId=" + id + "&managerPwd=" + pwd, 
			success: function(res){
				if (!res.checkAdminLogin) 
					alert("입력 정보가 일치하지 않습니다");
				else{
					alert("로그인 되었습니다")
					$('#mask, .window').hide();
					$('.window').hide();
					//게시판 관리 호출
					//boardManageAjax();
				}
			},
			error : function(err){
				alert(err);
			}
		});
}
</script>