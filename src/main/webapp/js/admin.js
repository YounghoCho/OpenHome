/*Board*/
function goBoardManageAjax(){
	$(".container.static").hide();
	$(".container.read").hide();
	$(".container.board").hide();
	$(".homeMainDiv").show();
	
	jQuery.ajax({
		type : "GET",
		url : "api/menuList",
		dataType : "json",
		success : function(res){ 
			//Removing Board Lists
			$(".tbody.admin > tr > td").remove();
			//Setting Board Lists
			for(var index = 0; index < res.menuList.length; index++){
			$(".tbody.admin").append(
					"<tr>" +
					"<td>" + res.menuList[index].boardTitle + "</td>" + 
					"<td><a class=\"removeArticle\"" + 
						"onclick=\"javascipr:updateBoard(" + res.menuList[index].boardNum + ")\">수정</a></td>" +
					"<td><a class=\"removeArticle\"" + 
					"onclick=\"javascipr:removeBoard(" + res.menuList[index].boardNum + ")\">삭제</a></td>" +
					"<td></td>" +
					"</tr>");
			}
		},
		error : function(err){
		alert(err);
		}
	});
	history.pushState({ data: '1' }, 'title1', '?depth=1');
}
function updateBoard(){
	alert("update");
}
function removeBoard(){
	alert("remove");
}

/*Articles*/
function goArticlesAjax(){
	$(".container.static").hide();
	$(".container.read").hide();
	$(".homeMainDiv").hide();
	$(".container.board").show();
	jQuery.ajax({
		type : "GET",
		url : "api/allArticles",
		dataType : "json",
		success : function(res){
			//Removing Article Lists
			$(".tbody > tr > td").remove();
			//Setting Article Lists
			for(var index = 0; index < res.allArticles.length; index++){
			$(".tbody").append(
					"<tr>" +
					"<td>" + res.allArticles[index].rownum + "</td>" +
					"<td><a href=\"javascript:goRead(" + res.allArticles[index].boardNum + ")\">"
					+ res.allArticles[index].articleSubject + "</a></td>" + 
					"<td>" + res.allArticles[index].boardTitle + "</td>" +
					"<td class=\"removeArticle\" onclick=\"javascipr:removeArticle(" + res.allArticles[index].articleNum + ")\">삭제</td></tr>");
			}
		},
		error : function(err){
			alert("너니?"+err);
		}
	});
	history.pushState({ data: '2' }, 'title2', '?depth=2');
}
/*Delete Article*/
function removeArticle(articleNum){
	if (!confirm("삭제하시겠습니까?")) {
        return;
    }
	$.ajax({
		type : "DELETE",
		url : "api/articleRemove?articleNum=" + articleNum,
		success : function(res){			
			if(res == "SUCCESS"){
				alert("삭제되었습니다.");
				goArticlesAjax();	//location.href할 필요가 없음.
			}
		},
		error : function(err){
			alert("Err:" + err);
		}
	});
}
/*Read Article*/
function goRead(articleNumber){
	$(".container.static").hide();
	$(".container.board").hide();
	$(".homeMainDiv").hide();
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

/*Graph*/
function goStaticGraphAjax(){
	$(".container.static").show();
	var traffics = new Array();
	
	jQuery.ajax({
		type : "GET",
		url : "api/trafficData",
		dataType : "json",
		data : "",
		
		success : function(res){
			var index = res.trafficData[0].trafficNum - 1;
			var length = res.trafficCount;
			for(var i = index; i < length; i++) {
				traffics[i] = res.trafficData[i].trafficContentLength;
				//alert(i+" : "+traffics[0]+" "+traffics[1]);
			}	
			//Graph
			Highcharts.chart('container', {
			    title: {
			        text: 'OPENWORKS 트래픽 그래프'
			    },
			    subtitle: {
			        text: '트래픽 그래프는 과금 정책과 직접적인 관련이 있습니다.'
			    },
			    yAxis: {
			        title: {
			            text: 'Traffic'
			        }
			    },
			    legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'middle'
			    },
			    plotOptions: {
			        series: {
			            label: {
			                connectorAllowed: false
			            },
			            pointStart: 1
			        }
			    },
				credits:{enabled: false},			
			    series: [{
			        name: '전체 트래픽',
			        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]	
			    }, {
			        name: '페이지 로딩',
			        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
			    }, {
			        name: '파일 업로드',
			        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
			    }, {
			        name: '파일 다운로드',
			        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
			    }, {
			    	name: '테스트 트래픽',
			    	data: traffics
			    }],
			
			    responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 500
			            },
			            chartOptions: {
			                legend: {
			                    layout: 'horizontal',
			                    align: 'center',
			                    verticalAlign: 'bottom'
			                }
			            }
			        }]
			    }
			});
			//end Graph
		},//end success
		error: function(err){
			alert(err);
		}
	});
	history.pushState({ data: '4' }, 'title4', '?depth=4');
}

/*Login*/
$(document).ready(function(){
	//세션정보없으면 검은막 띄우기(show())
	
	//검은막 띄우기
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();
	$("#mask").css({'width':maskWidth, 'height':maskHeight});
//	$("#mask").fadeIn(1000);
//	$("#mask").fadeTo("slow", 0.9);
//		$('.window').fadeIn(1000);
	goBoardManageAjax();
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

/*--- Page Back logic ---*/
$(window).bind("popstate", function(event) {
	try{
		var index=event.originalEvent.state.data;
		if (index == 1){
			$(".container.board").hide();
			$(".container.static").hide();
			$(".container.read").hide();
			$(".homeMainDiv").show();
		}
		else if(index == 2){
			$(".container.static").hide();
			$(".container.read").hide();
			$(".homeMainDiv").hide();
			$(".container.board").show();
		}
		else if(index == 3){
			$(".container.static").hide();
			$(".container.board").hide();
			$(".homeMainDiv").hide();
			$(".container.read").show();
		}
		else if(index == 4){
			$(".container.read").hide();
			$(".container.board").hide();
			$(".homeMainDiv").hide();
			$(".container.static").show();			
		}
	}catch(exception){	
		alert(exception);
	}
});