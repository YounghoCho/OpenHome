<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=8">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/board.css?ver=5">
</head>

<div>
	<div id="wrap" class="container">	<!--auto margin-->
		<div class="boardtitle"></div>

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

<!-- Load Jquery first, and then Bootstrap js -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>

<script>
//alert("test");
$(document).ready(function(){
	jQuery.ajax({
		type: "GET",
		url: "boardlist",
		dataType: "json",
		data: "boardNumberInt="+${boardNumberInt}+"&currentPageNo="+${currentPageNo},	// paging lists & from homecontroller
		success: function(res){
		
		//Paging			
			var pages=1;
			var countList=10;
			var countPage=10;
			var totalCount= res.countlist[0].countAll;
			var totalPage= totalCount/countList;
			var startPage= ((pages - 1) / 10) * 10 + 1;
			var endPage= startPage + countPage - 1;
			//handle exception
			if(totalCount % countList > 0){totalPage++;}
			if(totalPage < pages){pages= totalPage;}
			if(endPage > totalPage){endPage = totalPage;}
			//list page numbers
			for(var i=startPage; i<endPage; i++){
				if(startPage == i){	
					$("#indexNow").append("<a href=\"javascript:goPage("+startPage+")\">"
										+"<b>"+i+"</b></a>");
				}
				else{
					$("#indexOthers").append("<a href=\"javascript:goPage("+((i-1)*10+1)+")\">"
										+"<b>"+i+"</b></a>");
				}
			}			
			//set board title
			var boardIndex=res.messagelist[0].board_num;
			switch(boardIndex){
				case 1: $(".boardtitle").html("게시판1"); break;
				case 2: $(".boardtitle").html("게시판2"); break;
				case 3: $(".boardtitle").html("게시판3"); break;
				case 4: $(".boardtitle").html("게시판4"); break;
			}
			//set messagelists
			for(var index=0; index<res.messagelist.length; index++){
			$(".tbody").append("<tr>"
					+"<td>"+res.messagelist[index].rownum+"</td><td>"
					+"<a href=\"javascript:goRead("+res.messagelist[index].message_num+")\">"
					+res.messagelist[index].message_subject+"</a></td><td>"
					+res.messagelist[index].message_sample+"</td><td>"
					+res.messagelist[index].message_date.substring(0,10)+"</td><td>"
					+res.messagelist[index].message_writer+"</td>"+
					"</tr>");
		//Pagin End
			}
		},
		error: function(err){
			alert("lost");
		}
	});
});
</script>

<script>
function goPage(start){
	location.href= '?'+ "pages=" + start; //goto Homecontroller & currentPage will be changed
}
function goRead(message_num){
	location.href= 'read?'+ "message_num=" + message_num;
}
</script>
