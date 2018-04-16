/*--- First Page ---*/
$(document).ready(function(){
	getBoardListAjax();
	goHomeAjax();
});

/*--- menu-list ---*/
function getBoardListAjax(){
	jQuery.ajax({
		type : "GET",
		url : "api/board/boardList",
		dataType : "json",
		data : "",
		success : function(res){
			var len = res.boardList.length;
			for(index = 0; index < len; index++){
			$(".menudecoration").append("<li style=\"cursor:pointer;\" onclick = \"goBoardAjax(" + res.boardList[index].boardNum + ", 1)\">" + res.boardList[index].boardTitle + "</li>");
			}
		},
		error : function(err){
			alert(err);
		}
	});
}

/*---  body-home ---*/
function goHomeAjax(){
	$(".container.board").hide();
	$(".container.read").hide();
	$(".homeMainDiv").show();	
	
	jQuery.ajax({
		type : "GET",
		url : "api/board/boardList",
		dataType : "json",
		data : "",
		success : function(res){
			var len = res.boardList.length;
			var arrNum = new Array();	
		/*
		 * 동적 홈페이지 정렬 Algorithm
		 * 
		 * @Author : Youngho Jo 
		 */
			
		// 1. 게시판 순서를 불러오고, 각 게시판의 고유 번호를 배열에 저장한다.
			for (var index = 0; index < len; index++)		
				arrNum.push(res.boardList[index].boardNum); //ex) 4,1,3,2,6

			$(".homeMainDiv > div.container.home").remove();
	
		// 2. 총 게시판의 개수 만큼 게시판의 틀을 그린다.	
			var idIncreased = 1;
			for(var index = 0; index < len; index++, idIncreased++){
				$(".homeMainDiv").append(
						"<div class=\"container home\">" +
							"<table class=\"table\">" +
								"<tr>" + 
									"<th colspan=\"5\">" +
										"<a class=\"boardtitle\" href=\"javascript:goBoardAjax(" + arrNum[index] + ",1)\">" + res.boardList[index].boardTitle + "</a>" +
									"</th>" +
									"<th>작성날짜</th>" +
								"</tr>" +
								"<tbody id=\"" + idIncreased + "Message\">" + 
								"</tbody>" +
							"</table>" +		
						"</div>");
			}		

		// 3. 게시판 내용들을 불러온다 (articleList0, 1, 2...) 
			jQuery.ajax({
				type : "GET",
				url : "api/article/homeList",
				dataType : "json",
				data: {"stringArray" : arrNum, "boardCount" : len}, 
		
				success : function(res){					
		// 4. 총 게시판의 개수만큼 반복할 것이며,
					for(var idIncreased2 = 1; idIncreased2 <= len; idIncreased2++){ 
		// 5. 중복 코드를 줄이기 위해 동적 변수를 사용하여, 각 게시판의 데이터 객체를 저장한다.						
						eval("active = res.articleList" + (arrNum[idIncreased2 - 1] - 1));
						
		// 6. 첫번 째 게시판의 내용 갯수를 기본 길이로 설정하고, 게시판 내용을 그려넣는다. 
						var articleLen = active.length;
						for(var index = 1; index <= articleLen; index++){
						$("#" + idIncreased2 + "Message").append(
								"<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + 
								active[index-1].articleNum + ")\" class=\"boardtds\">" + active[index-1].articleTextContent + "</a></td>" +
								"<td>" + active[index-1].articleDate.substring(0,10) + "</td></tr>");		
		// 7. 내용 갯수를, 다음 순서의 게시판의 것으로 갱신하고 반복한다.
						articleLen = active.length;
						}
					}
				},
				error : function(err){
					alert("err");
				}
			}); //Inner Ajax End
			
		},
		error : function(err){
			alert(err);
		}
	}); //Outer Ajax End
	history.pushState({ data: '1' }, 'title2', '?depth=1');
}

/*--- body-board ---*/
function goBoardAjax(boardNumber, currentPageNo){
	//alert("Ajax goBoardAajx param boardNumber is :"+boardNumber+"\nAjax goBoardAajx param currentPageNo is :" + currentPageNo);
	$(".homeMainDiv").hide();
	$(".container.read").hide();
	$(".container.board").show();
	jQuery.ajax({
		type : "GET",
		url : "api/article/articleList",
		dataType : "json",
		data : "boardNumber=" + boardNumber + "&currentPageNo=" + currentPageNo,	// paging lists & from homecontroller
		success : function(res){
		
		//Paging			
			var pages = 1;
			var countList = 10;
			var countPage = 10;
			var totalCount = res.getArticleTotalCount;
			var totalPage = totalCount/countList;
			var startPage = ((pages - 1) / 10) * 10 + 1;
			var endPage = startPage + countPage - 1;
			//Exception Handling
			if(totalCount % countList > 0){ totalPage++; }
			if(totalPage < pages){ pages = totalPage;}
			if(endPage > totalPage){ endPage = totalPage;}
			$("#indexNow > a").remove();
			$("#indexOthers > a").remove();
			//Listing Up Page Numbers
			for (var i = startPage; i < endPage; i++){
				if (startPage == i){	
					$("#indexNow").append("<a href=\"javascript:goBoardAjax(" + boardNumber + "," + startPage + ")\">"
										 + "<b>" + i + "</b></a>");
				}
				else{
					$("#indexOthers").append("<a href=\"javascript:goBoardAjax(" + boardNumber + "," + ((i - 1) * 10 + 1) + ")\">"
										 + "<b>" + i + "</b></a>");
				}
			}			
			//Setting Board Title
			var boardIndex = res.articleList[0].boardNum;
			switch(boardIndex){
				case 1: $(".boardtitle.tt").html("게시판1"); break;
				case 2: $(".boardtitle.tt").html("게시판2"); break;
				case 3: $(".boardtitle.tt").html("게시판3"); break;
				case 4: $(".boardtitle.tt").html("게시판4"); break;
			}
			//Removing Message Lists
			$(".tbody > tr > td").remove();
			//Setting Message Lists
			for(var index = 0; index < res.articleList.length; index++){
			$(".tbody").append("<tr>"
					 + "<td>" + res.articleList[index].rownum + "</td><td>"
					 + "<a href=\"javascript:goRead(" + res.articleList[index].articleNum + ")\">"
					 + res.articleList[index].articleSubject + "</a></td><td>"
					 + res.articleList[index].articleTextContent + "</td><td>"
					 + res.articleList[index].articleDate.substring(0,10) + "</td><td>"
					 + res.articleList[index].articleWriter + "</td>" + 
					"</tr>");
			}
		//Pagin End
		},//Success End
		error : function(err){
			alert("lost");
		}
	});
	history.pushState({ data: '2' }, 'title2', '?depth=2');
};

/*--- body-Read ---*/
function goRead(articleNumber){
	$(".homeMainDiv").hide();
	$(".container.board").hide();
	$(".container.read").show();
	jQuery.ajax({
		type: "GET",
		url: "api/article/articleDetails",
		dataType: 'json',
		data: 'articleNumber='+ articleNumber,
		success: function(res){
			$("#boardTdSubject").html(res.articleDetails[0].articleSubject);
			$("#boardTdContent").html(res.articleDetails[0].articleContent);
		},
		error: function(err){
			alert("lose:"+err.status);
		}
	});
	history.pushState({ data: '3' }, 'title3', '?depth=3');
}

/*--- Page Back logic ---*/
$(window).bind("popstate", function(event) {
	try{
		var index=event.originalEvent.state.data;
		if (index == 2){
			$(".container.read").hide();
			$(".homeMainDiv").hide();
			$(".container.board").show();
		}
		else if (index == 1){
			$(".container.read").hide();
			$(".container.board").hide();
			$(".homeMainDiv").show();
		}
	}catch(exception){	
		alert(exception);
		$(".container.board").hide();
		$(".container.read").hide();
		$(".homeMainDiv").show();
	}
});