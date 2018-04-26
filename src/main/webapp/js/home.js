/*enum*/
var ReturnStatus = {"SUCCESS":"SUCCESS"};
Object.freeze(ReturnStatus); //Object.freeze : 값 수정을 방지한다.

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
			let len = res.boardList.length;
			for(let index = 0; index < len; index++){
				$(".menudecoration").append("<li style=\"cursor:pointer;\" onclick = \"goBoardAjax(" + res.boardList[index].boardNum + ", 1)\">" + res.boardList[index].boardTitle + "</li>");
				}
			/*
			 * Algorithm (Traffic)
			 * 
			 * @Author : 조영호
			 * 
			 * 1. response 객체의 숫자, 문자열을 구분한다
			 * 2. 숫자의 Byte를 게산하는 함수와, 전체 문자열의 Byte를 구하는 함수를 선언한다.
			 * 3. 숫자는 (/)연산을 사용하고, 문자열은 str.charCodeAt(i)과 비트연산(>>)을 사용한다.
			 * 4. Ajax로 트래픽 크기를 삽입한다.
			 */	
			/////////////Start////////////
			let trafficInt = new Array();
			let trafficString = new Array();
			let obj = res.boardList;
			let totalData = 0;

			for(let x in obj)
				trafficInt.push(obj[x].boardNum, obj[x].boardOrderNum);
			for(let x in obj)
				trafficString.push(obj[x].boardTitle);

			totalData += calculateIntLength(trafficInt, trafficInt.length);	//배열과 문자열개수를 인자로 넘긴다.
			totalData += calculateStringLength(trafficString, trafficString.length);	//배열과 문자열개수를 인자로 넘긴다.
			let trafficKind = "read";
			if(totalData != 0)
				insertTrafficAjax(totalData, trafficKind);
			/////////////End////////////
		},
		error : function(err){
			alert(err);
		}
	});
}

function insertTrafficAjax(trafficContentLength, trafficKind){
	jQuery.ajax({
		type : "POST",
		url : "api/traffic/contentLength",
		data : "trafficContentLength=" + trafficContentLength + "&trafficKind=" + trafficKind,
		success : function(res){
			if(res == ReturnStatus.SUCCESS){				
			}
		},
		error : function(err){
			alert("err" + err);
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
		url : "api/board/boardList",
		dataType : "json",
		data : "",
		success : function(res){		
		/*
		 * 동적 홈페이지 정렬 Algorithm
		 * 
		 * @Author : Youngho Jo 
		 */
			var len = res.boardList.length;
			var arrNum = new Array();	
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
									"<th colspan=\"4\">" +
										"<a class=\"boardtitle\" href=\"javascript:goBoardAjax(" + arrNum[index] + ",1)\">" + res.boardList[index].boardTitle + "</a>" +
									"</th>" +
									"<th>작성날짜</th>" +
								"</tr>" +
								"<tbody id=\"" + idIncreased + "Message\">" + 
								"</tbody>" +
							"</table>" +		
						"</div>");
			}	
			
			/////////////Start////////////
			let trafficInt = new Array();
			let trafficString = new Array();
			let obj = res.boardList;
			let totalData = 0;

			for(let x in obj)
				trafficInt.push(obj[x].boardNum, obj[x].boardOrderNum);
			for(let x in obj)
				trafficString.push(obj[x].boardTitle);

			totalData += calculateIntLength(trafficInt, trafficInt.length);	//배열과 문자열개수를 인자로 넘긴다.
			totalData += calculateStringLength(trafficString, trafficString.length);	//배열과 문자열개수를 인자로 넘긴다.
			let trafficKind = "read";
			if(totalData != 0)
				insertTrafficAjax(totalData, trafficKind);
			/////////////End////////////	
			
		// 3. 게시판 내용들을 불러온다 (articleList0, 1, 2...) 
			jQuery.ajax({
				type : "GET",
				url : "api/article/homeList",
				dataType : "json",
				data : {"stringArray" : arrNum, "boardCount" : len}, 
		
				success : function(res){					
		// 4. 총 게시판의 개수만큼 반복할 것이며,
					for(var idIncreased2 = 1; idIncreased2 <= len; idIncreased2++){ 
		// 5. 중복 코드를 줄이기 위해 동적 변수를 사용하여, 각 게시판의 데이터 객체를 저장한다.						
						eval("active = res.homeList" + (arrNum[idIncreased2 - 1] - 1));
						
		// 6. 첫번 째 게시판의 내용 갯수를 기본 길이로 설정하고, 게시판 내용을 그려넣는다. 
						var homeListLen = active.length;
						for(var index = 1; index <= homeListLen; index++){
							$("#" + idIncreased2 + "Message > tr").remove();
						}
						for(var index = 1; index <= homeListLen; index++){
						$("#" + idIncreased2 + "Message").append(
								"<tr><td colspan=\"4\"><a href=\"javascript:goRead(" + 
								active[index-1].articleNum + ")\" class=\"boardtds\">" + active[index-1].articleTextContent + "</a></td>" +
								"<td>" + active[index-1].articleDate.substring(0,10) + "</td></tr>");		
		// 7. 내용 갯수를, 다음 순서의 게시판의 것으로 갱신하고 반복한다.
						articleLen = active.length;
						}
					}
				
					/////////////Start////////////
					let trafficInt = new Array();
					let trafficString = new Array();
					let obj = res.homeList;
					let totalData = 0;

					for(let x in obj)
						trafficInt.push(obj[x].rownum, obj[x].boardNum, obj[x].articleNum);
					for(let x in obj)
						trafficString.push(obj[x].articleSubject, obj[x].articleTextContent, obj[x].articleDate, obj[x].articleWriter);

					totalData += calculateIntLength(trafficInt, trafficInt.length);	//배열과 문자열개수를 인자로 넘긴다.
					totalData += calculateStringLength(trafficString, trafficString.length);	//배열과 문자열개수를 인자로 넘긴다.
					let trafficKind = "read";
					if(totalData != 0)
						insertTrafficAjax(totalData, trafficKind);
					/////////////End////////////	
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
	location.hash = '#/page:homex';
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

			$("#article_reg_ok_btn").css("display", "block");
			$("#article_modify_ok_btn").css("display", "none;");
			
			//add custom-data on table
			$('#singleBoardTable').removeData("boardNum");
			$("#singleBoardTable").data("boardNum", boardNumber);
			
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
			
			/////////////Start////////////
			let len = res.articleList.length;
			let trafficInt = new Array();
			let trafficString = new Array();
			let obj = res.articleList;
			let totalData = 0;

			for(let x in obj)
				trafficInt.push(obj[x].rownum, obj[x].boardNum, obj[x].articleNum);
			for(let x in obj)
				trafficString.push(obj[x].articleSubject, obj[x].articleTextContent, obj[x].articleDate, obj[x].articleWriter);

			totalData += calculateIntLength(trafficInt, trafficInt.length);	//배열과 문자열개수를 인자로 넘긴다.
			totalData += calculateStringLength(trafficString, trafficString.length);	//배열과 문자열개수를 인자로 넘긴다.
			let trafficKind = "read";
			if(totalData != 0)
				insertTrafficAjax(totalData, trafficKind);
			/////////////End////////////
			
		//Pagin End
		},//Success End
		error : function(err){
			alert("lost");
		}
	});
	location.hash = '#/page:board'+boardNumber;
};

/*--- body-Read ---*/
function goRead(articleNumber){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".articleWriteDiv").hide();
	$(".articleReadDiv").show();
	
	$('#boardTdSubject').empty();
	$('#boardTdContent').empty();
	$('.filelist_2').empty();
	
	
	jQuery.ajax({
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
			
			/////////////Start////////////
			let len = res.articleDetails.length;
			let trafficInt = new Array();
			let trafficString = new Array();
			let obj = res.articleDetails;
			let totalData = 0;

			for(let x in obj)
				trafficInt.push(obj[x].boardNum, obj[x].articleNum);
			for(let x in obj)
				trafficString.push(obj[x].articleSubject, obj[x].articleContent, obj[x].articleDate, obj[x].articleWriter, obj[x].articleAccessPwd);

			totalData += calculateIntLength(trafficInt, trafficInt.length);	//배열과 문자열개수를 인자로 넘긴다.
			totalData += calculateStringLength(trafficString, trafficString.length);	//배열과 문자열개수를 인자로 넘긴다.
			let trafficKind = "read";
			if(totalData != 0)
				insertTrafficAjax(totalData, trafficKind);
			/////////////End////////////	

		},
		error: function(err){
			alert("lose:"+err.status);
		}
	});
	
	$.ajax({
		type: "post",
		url: "api/attachmentfile/fileDetails",
		dataType: 'json',
		data: 'articleNumber='+ articleNumber,
		success: function(res) {
			if(res.size != 0) {
				$.each(res, function(index, value) {
					$("#boardTdFiles > ul").append('<li class="filelist_2"><span><i class="far fa-file"></i></span><a href="/OpenHome/file/' + value.storedFileName + '"' + 'download="' + value.originalFileName + '">'
							+ value.originalFileName + '</a></li>');
				});
			} 
		},
		error : function(err) {
			alert('readyState:' + err.readyState);
			alert('status:' + err.status);
			alert('statusText:' + err.statusText);
			alert('responseText:' + err.responseText);
		}
	});
	location.hash = '#/page:readx'+articleNumber;
}
var temp = "";
/*--- Page Back logic ---*/
$(window).on('hashchange', function(){
	var page = location.hash.slice(7,12);
	temp=page;
	var num = location.hash.slice(12);
	//alert("page:"+page+" ,num:"+num);
	switch(page){
		case "homex":
			$(".homeMainDiv").show();
			$(".homeReadDiv").hide();
			$(".articleWriteDiv").hide();
			$("#singleBoard").hide();
			break;
		case "board":
			$("#singleBoard").show(); 
			$(".homeReadDiv").hide();
			$(".homeMainDiv").hide();
			$(".articleWriteDiv").hide();
			break;
		case "readx":
			$(".homeReadDiv").show();
			$(".homeMainDiv").hide();
			$(".articleWriteDiv").hide();
			$("#singleBoard").hide(); 
			break;
	}
});
/*발견 2
 * : 컨트롤러들로 구성했으나, SPA로 바꾸게 되었다. 이유는 새로고침 없이 페이지가 동적으로 변하고 관리도 쉽기 때문.
 *   한페이지에서 모든 요청이 ajax로 처리되므로 history관리를 할 수 없다. 즉,새로고침과 뒤로가기의 문제가 발생한다.
 *   이를 막기뤼해 Hash를 사용해 각 페이지에 anchor를 남겨준다.
 *   뒤로가기의 비밀은 hashchange에 있다.
 *   초기에는 pushstate를 사용했지만, IE 10이상에서만 지원하기 때문에 hash방식을 사용했다.
 *   
 */ 


$(window).on('DOMContentLoaded', function() {
	//새로고침이 됐을때, URL이 먼저 홈으로 바뀌고 새로고침 액션을 감지하게 된다.
	//왜냐하면 새로고침 했을때 HomeController로 들어오기 때문이다.
	//스크립트도 리로드 되기때문에 여기서 뭔가 할 수는 없다.
	//그럼 여기서 컨트롤러로 정보를 넘겨주면 되지 않을까?
});

/*--- Traffic Function ---*/
function calculateIntLength(arr, len){
	let totalData = 0;
	let data;
	for(let index = 0; index < len; index++){	//정수 개수만큼 반복하고		 
		data = arr[index];	
		totalData += (parseInt(data / 1000) ? 4 : (parseInt(data / 100) ? 3 : (parseInt(data / 10) ? 2 : 1) ) );//각 정수의 자릿수를 구해서 크기를 저장한다.	
	}
	return totalData;
}

function calculateStringLength(arr, len){
	let totalData = 0;
	for(let index = 0; index < len; index++){	//문자열 개수만큼 반복하고
		for(let x=0, temp=0; data = arr[index].charCodeAt(x); x++){	//각 문자열의 문자 수 만큼 반복하면서 
			temp = (data >> 11 ? 3 : (data >> 7 ? 2 : 1));	//문자의 크기를 저장하여 반환한다.
			totalData += temp;					
		}
	}
	return totalData;
}