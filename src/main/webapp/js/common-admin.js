/*enum*/
var ReturnStatus = {"SUCCESS":"SUCCESS"};
Object.freeze(ReturnStatus); //Object.freeze : 값 수정을 방지한다.


//관리자 최초 진입, 페이지 새로 고침 
$(document).ready(function(){
	var refresh = location.hash.slice(9,14);
	switch(refresh){
		case "":
			goBoardManageAjax();
			break;
		case "homex":
			goBoardManageAjax();
			break;		
		case "board":	
			goArticlesAjax(1);			
			break;
		case "readx":
			goRead(number);
			break;
		case "graph":
			goStaticGraphAjax();
			break;	
		case "apigp":
			goApiGraphAjax();
			break;
	}
});

//뒤로가기
$(window).on('hashchange', function(){
	var page = location.hash.slice(9,14);
	var number = location.hash.slice(14);
	switch(page){
	case "homex":
		$("#singleBoard").hide();
		$(".homeReadDiv").hide();
		$(".staticGraphDiv").hide();
		$("#BubbleChart").hide();
		$(".homeMainDiv").show();
		break;
	case "board":
		$(".homeMainDiv").hide();
		$(".articleReadDiv").hide();
		$(".articleWriteDiv").hide();
		$("#BubbleChart").hide();
		$("#singleBoard").show();
		break;
	case "readx":
		$(".staticGraphDiv").hide();
		$(".homeMainDiv").hide();
		$("#singleBoard").hide();
		$(".articleWriteDiv").hide();
		$("#BubbleChart").hide();
		$(".homeReadDiv").show();			
		$('#boardTdSubject').empty();
		$('#boardTdContent').empty();
		$('.filelist_2').empty();	
		break;
	case "graph":
		$(".homeMainDiv").hide();
		$("#singleBoard").hide();
		$(".homeReadDiv").hide();	
		$("#BubbleChart").hide();
		$(".staticGraphDiv").show();
		break;		
	case "apigp":
		$(".homeMainDiv").hide();
		$("#singleBoard").hide();
		$(".homeReadDiv").hide();	
		$(".staticGraphDiv").hide();
		$("#BubbleChart").show();
		break;
	}
});

//로그인 창
function loginPane(){
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();
	$("#mask").css({'width':maskWidth, 'height':maskHeight});
	$("#mask").fadeIn(1000);
	$("#mask").fadeTo("slow", 0.9);
	$('.window').fadeIn(1000);
}
//로그인
function loginAjax(){
		var id = $("#managerId").val();
		var pwd = $("#managerPwd").val();
		$.ajax({
			type : "GET",
			url : "api/admin/adminLogin",
			dataType : "json",	//count로 1개인지 확인
			data : "managerId=" + id + "&managerPwd=" + pwd, 
			success: function(res){
				if (!res.adminLogin) 
					alert("입력 정보가 일치하지 않습니다");
				else{
					alert("로그인 되었습니다")
					$('#mask, .window').hide();
					$('.window').hide();
					$("#logOutButton").show();
					
					trafficTracking(); //트래픽 감지 시작
				}
			},
			error : function(err){
				alert(err);
			}
		});
}

//로그아웃
$("#logOutButton").on("click", function(){
	$.ajax({
		type : 'POST',
		url : 'api/admin/logOut',
		success : function(res){
			if(res == ReturnStatus.SUCCESS){
				alert("로그아웃 되었습니다.");
				location.href="admin";
			}
		},
		error : function(err){
			alert(err);
		}
	});
});