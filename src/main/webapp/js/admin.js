/*Board*/
function goBoardManageAjax(){
	$(".staticGraphDiv").hide();
	$(".homeReadDiv").hide();
	$("#singleBoard").hide();
	$(".homeMainDiv").show();
	
	jQuery.ajax({
		type : "GET",
		url : "api/board/boardList",
		dataType : "json",
		success : function(res){ 
			//Removing Board Lists
			$(".tbody.admin > tr > td").remove();
			//Setting Board Lists
			for(var index = 0; index < res.boardList.length; index++){
			$(".tbody.admin").append(
					"<tr id=\"tableSection\">" +
					"<td>" + res.boardList[index].boardTitle + "</td>" + 
					"<td><a class=\"updateBoard\"" + 
						"onclick=\"javascript:updateBoard(" + res.boardList[index].boardNum + ")\">수정</a></td>" +
					"<td><a class=\"removeBoard\"" + 
					"onclick=\"javascipr:removeBoard(" + res.boardList[index].boardNum + ")\">삭제</a></td>" +
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
//게시판 이름 수정
function updateBoard(boardNum){
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();
	$("#mask").css({'width':maskWidth, 'height':maskHeight});
	$("#mask").fadeIn(1000);
	$("#mask").fadeTo("slow", 0.9);
	$(".boardTitleWindow").fadeIn(1000);
	//최종 수정 버튼 클릭
	$("#updateBoardButton").off().on("click", function(){
		jQuery.ajax({
			type : "PUT",
			url : "api/board/boardTitles",
			data : { "boardTitle" : $("#newTitle").val(), "boardNum" : boardNum},
			success : function(res){			
				if(res == "SUCCESS"){
					alert("변경되었습니다.");
					$("#mask").hide();
					$(".boardTitleWindow").hide();	
					goBoardManageAjax();
					$("#newTitle").val('');
				}
			},
			error : function(err){
				alert(err);
			}
		});
	});
	
}
//Board 삭제
function removeBoard(boardNum){
	if (!confirm("삭제하시겠습니까? 모든 게시글이 삭제됩니다.")) {
        return;
    }
	$.ajax({
		type : "DELETE",
		url : "api/board/boardRemove?boardNum=" + boardNum,
		success : function(res){			
			if(res == "SUCCESS"){
				alert("삭제되었습니다.");
				goBoardManageAjax();
			}
		},
		error : function(err){
			alert("Err:" + err);
		}
	});
}
//Board 추가
$("#newBoardButton").on("click", function(){
	jQuery.ajax({
		type : "POST",
		url : "api/board/newBoard",
		data : "boardTitle=" + $("#boardTitle").val(),
		success : function(res){
			if(res == "SUCCESS"){
				alert("추가되었습니다.");
				$('#boardTitle').val('');
				goBoardManageAjax();
			}
		},
		error : function(err){
			alert("err" + err);
		}
	});
});
//Board 순서 조정
$("#orderButton").on("click", function(){		
		//검은막 띄우기
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
		$("#mask").css({'width':maskWidth, 'height':maskHeight});
		$("#mask").fadeIn(1000);
		$("#mask").fadeTo("slow", 0.9);
		$(".orderWindow").fadeIn(1000);
		//드래그앤 드롭
		var tableOrder = new Array();
		$( "#sortable" ).sortable({
			axis: "y",
			containment: "parent",
			update: function (event, ui) {
				tableOrder = $(this).sortable('toArray', {
					attribute: 'data-name'
				});
//				alert(tableOrder);
			}
		});
		//게시판 목록 보이기
		jQuery.ajax({
			type : "GET",
			url : "api/board/boardList",
			dataType : "json",
			success : function(res){ 
				for(index = 0; index < res.boardList.length; index++){
					$('#sortable').append(
						"<li id=\"sortableList\" class=\"sortableList" + index + "\" data-name=\'"+
						res.boardList[index].boardNum +
						"\'>" + 
						res.boardList[index].boardTitle +
						"</li>");
				}
				$('#sortable').append(
						"<button class=\"btn btn-success\" id=\"saveOrderButton\">순서 저장</button>");
				//Custom Attribute로 데이터 전송
				$("#saveOrderButton").on("click", function(){
					//순서 저장
					jQuery.ajax({
						type : "PUT",
						url : "api/board/boardOrders",
						data :  {"tableOrder" : tableOrder},
						success : function(res){
							if(res == "SUCCESS"){
								alert("게시판 순서가 변경 되었습니다.")
								$("#mask").hide();
								$(".orderWindow").hide();
								goBoardManageAjax();
							}
						},
						error : function(err){
							alert("에러"+err);
						}
					});
				});
			},
			error : function(err){
				alert(err);
			}
		});	
});

//순서 조정 창 닫기
$("#closeChange").on("click", function(){
	$("#mask").hide();
	$(".orderWindow").hide();
});
$("#closeChange2").on("click", function(){
	$("#mask").hide();
	$(".boardTitleWindow").hide();
});

/*Articles*/
function goArticlesAjax(){
	$(".staticGraphDiv").hide();
	$(".homeReadDiv").hide();
	$(".homeMainDiv").hide()
	$("#singleBoard").show();
	jQuery.ajax({
		type : "GET",
		url : "api/article/allArticles",
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
			alert(err);
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
		url : "api/article/articleRemove?articleNum=" + articleNum,
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
	$(".staticGraphDiv").hide();
	$(".homeMainDiv").hide()
	$("#singleBoard").hide();
	$(".homeReadDiv").show();
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

/*Graph*/
function goStaticGraphAjax(){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".homeReadDiv").hide();
	$(".staticGraphDiv").show();

	var traffics = new Array();
	
	jQuery.ajax({
		type : "GET",
		url : "api/traffic/trafficData",
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
			$("#singleBoard").hide();
			$(".homeReadDiv").hide();
			$(".staticGraphDiv").hide();
			$(".homeMainDiv").show();
		}
		else if(index == 2){
			$(".homeReadDiv").hide();
			$(".staticGraphDiv").hide();
			$(".homeMainDiv").hide();
			$("#singleBoard").show();
		}
		else if(index == 3){
			$(".staticGraphDiv").hide();
			$(".homeMainDiv").hide();
			$("#singleBoard").hide();
			$(".homeReadDiv").show();
		}
		else if(index == 4){
			$(".homeMainDiv").hide();
			$("#singleBoard").hide();
			$(".homeReadDiv").hide();		
			$(".staticGraphDiv").show();
		}
	}catch(exception){	
		alert(exception);
	}
});