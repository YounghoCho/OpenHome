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