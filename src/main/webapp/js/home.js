/*--- First Page ---*/
$(document).ready(function(){
	getMenuListAjax();
	/*goHomeAjax();*/
});

/*--- menu-list ---*/
function getMenuListAjax(){
	jQuery.ajax({
		type : "GET",
		url : "api/menuList",
		dataType : "json",
		data : "",
		success : function(res){
			var len = res.menuList.length;
			for(index = 0; index < len; index++){
			$(".menudecoration").append("<li style=\"cursor:pointer;\" onclick = \"goBoardAjax(" + res.menuList[index].boardNum + ", 1)\">" + res.menuList[index].boardTitle + "</li>");
			}
		},
		error : function(err){
			alert(err);
		}
	});
}

/*---  body-home ---*/
function goHomeAjax(){
	$("#singleBoard").hide();
	$(".articleReadDiv").hide();
	$(".articleWriteDiv").hide();
	$(".homeMainDiv").show();
	// Algoritm 1 : Reference is in getMenuListAjax()
	jQuery.ajax({
		type : "GET",
		url : "api/menuList",
		dataType : "json",
		data : "",
		success : function(res){
			var len = res.menuList.length;
			var arrNum = new Array();
			for(var index = 0; index < len; index++){
				arrNum.push(res.menuList[index].boardNum);
			}
			// Remove
			$(".homeMainDiv > div.container.home").remove();
			// Draw Home(4 Boards)
			for(var index = 0; index < len; index++){
				$(".homeMainDiv").append(
						"<div class=\"container home\">" +
							"<table class=\"table\">" +
								"<tr>" + 
									"<th colspan=\"5\">" +
										"<a class=\"boardtitle\" href=\"javascript:goBoardAjax(" + arrNum[index] + ",1)\">" + res.menuList[index].boardTitle + "</a>" +
									"</th>" +
									"<th>작성날짜</th>" +
								"</tr>" +
								"<tbody id=\""+arrNum[index]+"Message\">" + 
								"</tbody>" +
							"</table>" +		
						"</div>");
			}
			
			// Inner Ajax Start
			jQuery.ajax({
				type : "GET",
				url : "api/homeList",
				dataType : "json",
				data: {"stringArray" : arrNum, "boardCount" : len}, 
				success : function(res){ //res : articleList + index
					
					//len만큼 아래를 만복해야한다.
					
					// Remove
					$("#1Message > tr > td").remove();
					$("#2Message > tr > td").remove();
					$("#3Message > tr > td").remove();
					$("#4Message > tr > td").remove();
					// Draw Articles
					for(var i = 0; i < res.articleList0.length; i++){
						$("#1Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList0[i].articleNum + ")\" class=\"boardtds\">" + res.articleList0[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList0[i].articleDate.substring(0,10) + "</td></tr>");
					}
					for(var i = 0; i < res.articleList1.length; i++){
						$("#2Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList1[i].articleNum + ")\" class=\"boardtds\">" + res.articleList1[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList1[i].articleDate.substring(0,10) + "</td></tr>");				
					}
					for(var i = 0; i<res.articleList2.length; i++){
						$("#3Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList2[i].articleNum + ")\" class=\"boardtds\">" + res.articleList2[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList2[i].articleDate.substring(0,10) + "</td></tr>");
					}
					for(var i = 0; i < res.articleList3.length; i++){
						$("#4Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList3[i].articleNum + ")\" class=\"boardtds\">" + res.articleList3[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList3[i].articleDate.substring(0,10) + "</td></tr>");
					}
				},
				error : function(err){
					alert("err");
				}
			});//Inner Ajax End
			
		},
		error : function(err){
			alert(err);
		}
	});//Outer Ajax End

	history.pushState({ data: '1' }, 'title2', '?depth=1');
	
}

/*--- body-board ---*/
function goBoardAjax(boardNumber, currentPageNo){
	//alert("Ajax goBoardAajx param boardNumber is :"+boardNumber+"\nAjax goBoardAajx param currentPageNo is :" + currentPageNo);
	$(".homeMainDiv").hide();
	$(".articleReadDiv").hide();
	$(".articleWriteDiv").hide();
	$("#singleBoard").show();
	jQuery.ajax({
		type : "GET",
		url : "api/articleList",
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
	$("#singleBoard").hide();
	$(".articleWriteDiv").hide();
	$(".articleReadDiv").show();
	
	jQuery.ajax({
		type: "GET",
		url: "api/articleDetails",
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
			$(".articleReadDiv").hide();
			$(".homeMainDiv").hide();
			$(".articleWriteDiv").hide();
			$("#singleBoard").show();
		}
		else if (index == 1){
			$(".articleReadDiv").hide();
			$("#singleBoard").hide();
			$(".articleWriteDiv").hide();
			$(".homeMainDiv").show();
		}
	}catch(exception){	
		$("#singleBoard").hide();
		$(".articleReadDiv").hide();
		$(".articleWriteDiv").hide();
		$(".homeMainDiv").show();
	}
});