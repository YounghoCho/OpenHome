function setEndDate() {
	var date = new Date();

	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;
  
	var end = year + "-" + month + "-" + day;       
	$("#date-end").prop("value", end);
}

function setStartDate(value) {
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	value = value*1;
	
	day = day - value;
		
	while(day <= 0) {
		month = month - 1; //이전 달
		if (month <= 0) {  //이번 달이 1월인 경우 처리
			month = month + 12;
			year = year - 1;
		}
		var d = new Date(year, month, 0);
		day = d.getDate() + day;
	}

	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;
  
	var start = year + "-" + month + "-" + day;       
	$("#date-start").prop("value", start);
}

function cleanDateField(){
	$("#date-start").val("");
	$("#date-end").val("");
}

$('#date-select').on('change', function(){
	
	var term = $(this).val();
	if (term == "all") {
		cleanDateField();
	} else if (term == "1week") {
		setEndDate();
		setStartDate(7);
	} else if (term == "1month") {
		setEndDate();
		setStartDate(30);
	} else if (term == "3month") {
		setEndDate();
		setStartDate(90);
	} else if (term == "self") {
		cleanDateField();
	}
});

$('#date-start').on('change', function() {
	$('#date-select').val("self").prop("selected", true);
});

$('#date-end').on('change', function() {
	$('#date-select').val("self").prop("selected", true);
});

$('#searchbtn').on('click', searchArticle);

function searchArticle() {

	if (($('#content-text').val() == "") && ($('#writer-text').val() == "")) {
		alert("검색어를 입력하세요.");
		return false;
	}
	
	if (($('#date-start').val() != "") && ($('#date-end').val() == "")) {
		setEndDate();
	}
	
	if ($('#date-select').val() == "self" && ($('#date-start').val() > $('#date-end').val())) {
		alert("검색 날짜범위를 다시 입력하세요.");
		return false;
	}
	
	if (($('#date-start').val() == "") && ($('#date-end').val() != "")) {
		alert("검색 시작일을 입력하세요.");
		return false;
	}
	
	var param = {
			"boardnum"		: $('#board-select').val(), 
			"searchType"	: $('#content-select').val(),
			"content"		: $('#content-text').val(),
			"writer"		: $('#writer-text').val(),
			"fileAnswer"	: $('#file-checkbox').prop('checked'),
			"commentAnswer"	: $('#comment-checkbox').prop('checked'),
			"startDate"		: $('#date-start').val(),
			"endDate"		: $('#date-end').val()
	}
	
	var json_data = JSON.stringify(param);
	/*alert(json_data);*/
	
	$.ajax({
		url			: 'api/article/searchArticle',
		type		: 'post',
		dataType	: 'json',
		data		: json_data,
		contentType	: 'application/json;charset=utf-8',
		cache		: false,
		success		: function(res) {
			$(".homeMainDiv").hide();
			$(".articleReadDiv").hide();
			$(".articleWriteDiv").hide();
			$("#singleBoard").show();
//			alert(res.size);
		//
		$(".tbody > tr").remove();
		$("#indexNow > a").remove();
		$("#indexOthers > a").remove();
		
		if(res.length == 0) {
			$(".tbody").append('<tr><td colspan="5" id="no_result">검색결과가 없습니다.</td></tr>');
		} else {
			$.each(res, function(index, value) {
				$(".tbody").append("<tr id='"+ value.rownum + "'>"
						 + "<td>" +value.rownum + "</td><td>"
						 + "<a href=\"javascript:goRead(" + value.articleNum + ")\">"
						 + value.articleSubject + "</a></td><td>"
						 + value.articleTextContent + "</td><td>"
						 + value.articleDate.substring(0,10) + "</td><td>"
						 + value.articleWriter + "</td>" + 
						"</tr>");
				})
		}
		
		/*$("#indexNow").append("<a href=\"javascript:goBoardAjax(" + boardNumber + "," + startPage + ")\">"
				 + "<b>" + i + "</b></a>");
		
		
		}
			
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
		

		$.each(res, function(index, value) {
			alert(value.articleNum);
		})*/
	},
	error		: function(err) {
		alert('readyState:' + err.readyState);
		alert('status:' + err.status);
		alert('statusText:' + err.statusText);
		alert('responseText:' + err.responseText);
	}
});
	/*location.hash = '/#/page:board'+boardNumber;*/

}

function goSearchBoard() {
	$(".homeMainDiv").hide();
	$(".articleReadDiv").hide();
	$(".articleWriteDiv").hide();
	$("#singleBoard").show();
	
	
	
}


