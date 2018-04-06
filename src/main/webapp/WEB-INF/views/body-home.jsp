<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/home.css?ver=10">
</head>

<!-- body-home -->
<div style="margin:10px;" class="homeMainDiv">
	<!-- 1st -->
	<div id="wrap" class="container home">
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
	<div id="wrap" class="container home">
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
	<div id="wrap" class="container home">
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
	<div id="wrap" class="container home">
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
<div>
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
<div>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home.js?ver=8"></script>
<script>

///// locate Page and active Ajax 
/* cannot use "$" in js file.
 * boardNumberInt comes from meun.jsp
 * Make network loading time be decreased.
 */
if(${boardNumberInt}==0){
	goHomeAjax();		  
}
if(${boardNumberInt}!=0)
	goBoardAjax(${boardNumberInt}, ${currentPageNo});

///// Page Back logic /////
//if fail to go back, relocated to it's board page
$(window).bind("popstate", function(event) {
	//if get error as go back, re-locate to it's board
	try{
		var index=event.originalEvent.state.data;
		if(index==2){
			$(".container.read").hide();
			$(".container.board").show();
		}
		else if(index==1){
			location.href="board"+${boardNumberInt};
		}
	}catch(exception){
		alert(exception);
		location.href="board"+${boardNumberInt};
	}
});
</script>
