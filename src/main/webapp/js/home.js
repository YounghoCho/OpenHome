//enum 선언
var ReturnStatus = {"SUCCESS":"SUCCESS"};
Object.freeze(ReturnStatus);

//사용자 최초 진입, 페이지 새로 고침
$(document).ready(function(){
	var refresh = location.hash.slice(9,14);
	var number = location.hash.slice(14);
	switch(refresh){
		case "":
			goHomeAjax();
			break;
		case "homex":
			goHomeAjax();
			break;		
		case "board":	
			getBoardListAjax();
			goBoardAjax(number, 1);//(게시판번호, 페이지번호)
			break;
		case "readx":
			getBoardListAjax();
			goRead(number);//(게시글번호)
			break;
	}
});

//페이지 뒤로가기
$(window).on('hashchange', function(){
	var page = location.hash.slice(9,14);
	var number = location.hash.slice(14);
	switch(page){
		case "homex":
			$("#singleBoard").hide();
			$(".articleReadDiv").hide();
			$(".articleWriteDiv").hide();
			$(".homeMainDiv").show();
			break;
		case "board":
			$(".homeMainDiv").hide();
			$(".articleReadDiv").hide();
			$(".articleWriteDiv").hide();
			$("#singleBoard").show();
			break;
		case "readx":
			$(".homeMainDiv").hide();
			$("#singleBoard").hide();
			$(".articleWriteDiv").hide();
			$(".articleReadDiv").show();			
			$('#boardTdSubject').empty();
			$('#boardTdContent').empty();
			$('.filelist_2').empty();
			break;
	}
});

//게시판 목록
function getBoardListAjax(){
	$.ajax({
		type : "GET",
		url : "api/article/boardList",
		dataType : "json",
		data : "",
		success : function(res){
			let len = res.boardList.length;
			for (let index = 0; index < len; index++){
				$(".menudecoration").append("<li style = \"cursor:pointer;\" onclick = \"goBoardAjax(" + res.boardList[index].boardNum + ", 1)\">" + res.boardList[index].boardTitle + "</li>");
				/* top의 board select에 추가*/
				$("#board-select").append('<option value="' + res.boardList[index].boardNum + '">' + res.boardList[index].boardTitle + '</option>');
			}
		},
		error : function(err){
			alert("getBoardListAjax error : " + err);
		}
	});
}

//홈페이지
function goHomeAjax(){
	$("#singleBoard").hide();
	$(".articleReadDiv").hide();
	$(".articleWriteDiv").hide();
	$(".homeMainDiv").show();

	$.ajax({
		type : "GET",
		url : "api/article/boardList",
		dataType : "json",
		data : "",
		success : function(res){	
			//게시판 목록 그리기 기능
			let len = res.boardList.length;
			$(".menudecoration > #menuButton").remove();
			for (let index = 0; index < len; index++){
				$(".menudecoration").append("<li id = \"menuButton\"style = \"cursor:pointer;\" onclick = \"goBoardAjax(" + res.boardList[index].boardNum + ", 1)\">" + res.boardList[index].boardTitle + "</li>");
			}

		//관리자가 조작한 순서대로 게시판을 출력하는 기능
			var arrNum = new Array();	
			//1. 게시판의 순서를 배열에 담는다.
			for (var index = 0; index < len; index++)		
				arrNum.push(res.boardList[index].boardNum);
			//2. 게시판들의 틀을 그린다.
			$(".homeMainDiv > div.container.home").remove();
			var idIncreased = 1;
			for (var index = 0; index < len; index++, idIncreased++){
				$(".homeMainDiv").append(
						"<div class = \"container home\">" +
							"<table class = \"table\">" +
								"<tr>" +
									"<th colspan = \"4\">" +
										"<a class = \"boardtitle\" href = \"javascript:goBoardAjax(" + arrNum[index] + ",1)\">" + res.boardList[index].boardTitle + "</a>" +
									"</th>" +
									"<th>작성날짜</th>" +
								"</tr>" +
								"<tbody id = \"" + idIncreased + "Message\">" +
								"</tbody>" +
							"</table>" +		
						"</div>");
			}	
			//3. 게시판 내용들을 채워 넣는다.
			$.ajax({
				type : "GET",
				url : "api/article/homeList",
				dataType : "json",
				data : {"stringArray" : arrNum, "boardCount" : len}, 		
				success : function(res){					
					for (var idIncreased2 = 1; idIncreased2 <= len; idIncreased2++){
						eval("active = res.homeList" + (arrNum[idIncreased2 - 1] - 1)); //동적 변수로 중복 코드를 줄였다.						

						var homeListLen = active.length;
						for (var index = 1; index <= homeListLen; index++){
							$("#" + idIncreased2 + "Message > tr").remove();
						}
						for (var index = 1; index <= homeListLen; index++){
							$("#" + idIncreased2 + "Message").append(
								"<tr><td colspan = \"4\"><a href = \"javascript:goRead(" +
								active[index-1].articleNum + ")\" class = \"boardtds\">" + active[index-1].articleTextContent + "</a></td>" +
								"<td>" + active[index-1].articleDate.substring(0,10) + "</td></tr>");		
			//4. 그 다음 게시판의 내용을 그린다.
						articleLen = active.length;
						}
					}
				},
				error : function(err){
					alert("HomeList error : " + err);
				}
			});			
		},
		error : function(err){
			alert("goHomeAjax error : " + err);
		}

	}); //Outer Ajax End
	location.hash = '/#/page:homex';
}

//게시판 목록
function goBoardAjax(boardNumber, currentPageNo){
	$(".homeMainDiv").hide();
	$(".articleReadDiv").hide();
	$(".articleWriteDiv").hide();
	$("#singleBoard").show();

	$.ajax({
		type : "GET",
		url : "api/article/articleList",
		dataType : "json",
		data : "boardNumber=" + boardNumber + "&currentPageNo=" + currentPageNo,
		success : function(res){
			//페이징			
			var pages = 1;
			var countList = 10;
			var countPage = 10;
			var totalCount = res.getArticleTotalCount;
			var totalPage = totalCount/countList;
			var startPage = ((pages - 1) / 10) * 10 + 1;
			var endPage = startPage + countPage - 1;
			//예외처리
			if (totalCount % countList > 0){ totalPage++; }
			if (totalPage < pages){ pages = totalPage;}
			if (endPage > totalPage){ endPage = totalPage;}
			//페이지 번호 그리기
			$("#indexNow > a").remove();
			$("#indexOthers > a").remove();
			for (var i = startPage; i < endPage; i++){
				if (startPage == i){	
					$("#indexNow").append("<a href = \"javascript:goBoardAjax(" + boardNumber + "," + startPage + ")\">"
										 + "<b>" + i + "</b></a>");
				}
				else{
					$("#indexOthers").append("<a href = \"javascript:goBoardAjax(" + boardNumber + "," + ((i - 1) * 10 + 1) + ")\">"
										 + "<b>" + i + "</b></a>");
				}
			}			

			$("#article_reg_ok_btn").css("display", "block");
			$("#article_modify_ok_btn").css("display", "none;");
			//add custom-data on table
			$('#singleBoardTable').removeData("boardNum");
			$("#singleBoardTable").data("boardNum", boardNumber);		
			//Removing Message Lists
			$(".tbody > tr > td").remove();
			//Setting Message Lists
			for (var index = 0; index < res.articleList.length; index++){
			$(".tbody").append("<tr>"
					 + "<td>" + res.articleList[index].rownum + "</td><td>"
					 + "<a href = \"javascript:goRead(" + res.articleList[index].articleNum + ")\">"
					 + res.articleList[index].articleSubject + "</a></td><td>"
					 + res.articleList[index].articleTextContent + "</td><td>"
					 + res.articleList[index].articleDate.substring(0,10) + "</td><td>"
					 + res.articleList[index].articleWriter + "</td>" +
					"</tr>");
			}
		},
		error : function(err){
			alert("goBoardAjax error : " + err);
		}
	});
	location.hash = '/#/page:board'+boardNumber;
};

//게시글 읽기
function goRead(articleNumber){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".articleWriteDiv").hide();
	$(".articleReadDiv").show();	
	$('#boardTdSubject').empty();
	$('#boardTdContent').empty();
	$('.filelist_2').empty();
		
	$.ajax({
		type: "GET",
		url: "api/article/articleDetails",
		dataType: 'json',
		data: 'articleNumber='+ articleNumber,
		success: function(res){
			$("#boardTdSubject").html(res.articleDetails[0].articleSubject);
			$("#boardTdContent").html(res.articleDetails[0].articleContent);
			$("#readtable").data("articleNum", res.articleDetails[0].articleNum);			
			//add custom-data on table
			$('#singleBoardTable').removeData("boardNum");
			$("#singleBoardTable").data("boardNum", res.articleDetails[0].boardNum);	
		},
		error: function(err){
			alert("goRead error : " + err);
		}
	});
	
	$.ajax({
		type: "post",
		url: "api/attachmentfile/fileDetails",
		dataType: 'json',
		data: 'articleNumber='+ articleNumber,
		success: function(res) {
			if (res.size != 0) {
				$.each(res, function(index, value) {
					$("#boardTdFiles > ul").append('<li class = "filelist_2"><span><i class = "far fa-file"></i></span><a href = "/OpenHome/file/' + value.storedFileName + '"' + 'download = "' + value.originalFileName + '">'
							+ value.originalFileName + '</a></li>');
				});
			}
		},
		error : function(err) {
			alert('fileDetails error : ' + err);
		}
	});
	location.hash = '/#/page:readx'+articleNumber;
}