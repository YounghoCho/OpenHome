/*--- First Page ---*/
$(document).ready(function(){
	getMenuListAjax();
	goHomeAjax();
});

/*--- menu-list ---*/
function getMenuListAjax(){
	jQuery.ajax({
		type : "GET",
		url : "api/menuList",
		dataType : "json",
		data : "",
		success : function(res){
			/*
			 * Algoritm 1 : Menu List (DB orderby의 부하를 줄인다, 데이터size*컬럼개수*10000)
			 * 
			 * @Author : Youngho Jo
			 * 
			 * 1. 게시판 인덱스, 게시판 타이틀, 게시판 순서 컬럼을 디비에서 받아온다.
			 * 2. response 객체의 길이만큼 배열 두개를 생성한다.
			 * 3. 동적 데이터인 게시판의 순서에 따라서, 정적 데이터인 게시판 인덱스와 타이틀을 기록한다.
			 * 4. arrNum에는 고유의 번호를 기록하고, arrTitle에는 고유의 타이틀을 저장한다.
			 * 5. 기록 된 순서대로 append를 통해 메뉴 목록을 구성한다.
			 */
			var len = res.menuList.length;
			var arrNum = new Array(len + 1);
			var arrTitle = new Array(len + 1);
			for(var index = 0; index < len; index++){
				arrNum[res.menuList[index].boardOrderNum] = res.menuList[index].boardNum;
				arrTitle[res.menuList[index].boardOrderNum] = res.menuList[index].boardTitle;
			}	
			//alert(arrNum[0] + ", " + arrNum[1] + ", " + arrNum[2] + ", " + arrNum[3] + ", " + arrNum[4]);
			for(index = 1; index <= len; index++){
				$(".menudecoration").append("<li style=\"cursor:pointer;\" onclick = \"goBoardAjax(" + arrNum[index] + ", 1)\">" + arrTitle[index] + "</li>");
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
	
	// Algoritm 1 : Reference is in getMenuListAjax()
	jQuery.ajax({
		type : "GET",
		url : "api/menuList",
		dataType : "json",
		data : "",
		success : function(res){
			var len = res.menuList.length;
			var arrNum = new Array(len + 1);
			var arrTitle = new Array(len + 1);
			for(var index = 0; index < len; index++){
				arrNum[res.menuList[index].boardOrderNum] = res.menuList[index].boardNum;
				arrTitle[res.menuList[index].boardOrderNum] = res.menuList[index].boardTitle;
			}
			// Inner Ajax Start
			jQuery.ajax({
				type : "GET",
				url : "api/homeList",
				dataType : "json",
				data: {"stringArray" : arrNum}, 
				success : function(res){
					// Remove
					$(".homeMainDiv > div.container.home").remove();
					
					$("#1stMessage > tr > td").remove();
					$("#2ndMessage > tr > td").remove();
					$("#3rdMessage > tr > td").remove();
					$("#4thMessage > tr > td").remove();
					
					// Draw Home(4 Boards)
					for(var index = 1; index <= len; index++){
						$(".homeMainDiv").append(
								"<div class=\"container home\">" +
									"<table class=\"table\">" +
										"<tr>" + 
											"<th colspan=\"5\">" +
												"<a class=\"boardtitle\" href=\"javascript:goBoardAjax(" + arrNum[index] + ",1)\">" + arrTitle[index] + "</a>" +
											"</th>" +
											"<th>작성날짜</th>" +
										"</tr>" +
										"<tbody id=\""+arrNum[index]+"Message\">" + 
										"</tbody>" +
									"</table>" +		
								"</div>");
					}
					
					// Draw Articles
					for(var i = 0; i < res.articleList1.length; i++){
						$("#1Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList1[i].articleNum + ")\" class=\"boardtds\">" + res.articleList1[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList1[i].articleDate.substring(0,10) + "</td></tr>");
					}
					for(var i = 0; i < res.articleList2.length; i++){
						$("#2Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList2[i].articleNum + ")\" class=\"boardtds\">" + res.articleList2[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList2[i].articleDate.substring(0,10) + "</td></tr>");				
					}
					for(var i = 0; i<res.articleList3.length; i++){
						$("#3Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList3[i].articleNum + ")\" class=\"boardtds\">" + res.articleList3[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList3[i].articleDate.substring(0,10) + "</td></tr>");
					}
					for(var i = 0; i < res.articleList4.length; i++){
						$("#4Message").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead(" + res.articleList4[i].articleNum + ")\" class=\"boardtds\">" + res.articleList4[i].articleTextContent + "</a></td>"
								 + "<td>" + res.articleList4[i].articleDate.substring(0,10) + "</td></tr>");
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
	$(".container.read").hide();
	$(".container.board").show();
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
	$(".container.board").hide();
	$(".container.read").show();
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