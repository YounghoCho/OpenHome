//API 호출 합계
var totalApiCall, totalArticleList, totalArticleDetail, totalArticleWrite, totalFileUpload, totalFileDownload;

//API 그래프 그리기.
function goApiGraphAjax(){
	$(".homeMainDiv").hide();
	$("#singleBoard").hide();
	$(".homeReadDiv").hide();
	$(".staticGraphDiv").hide();
	$(".apiGraphDiv").show();
	$("#DonutChartHead").hide();
	$("#DonutChart").hide();
	$("#BubbleChartHead").show();
	$("#BubbleChart").show();		
	
	$.ajax({
		type : "GET",
		url : "api/apiCall/apiList", //response : totalApiList
		dataType : "json",
		success : function(res){
			var articleList = new Array();
			var articleDetail = new Array();
			var articleWrite = new Array();
			var fileUpload = new Array();
			var fileDownload = new Array();
			var chartDate = new Array();
			
			//전체 api 개수만큼 반복한다.
			var memo = 0, temp1, temp2, temp3, temp4, temp5;
			var length = res.totalApiList.length;
			var startDate = Date.parse(res.totalApiList[0].apiDate.substring(0,10));//트래픽 시작날짜
			var endDate = Date.parse(res.totalApiList[length-1].apiDate.substring(0,10));//트래픽 최신날짜
			//alert(length + ", " + startDate + ", " + endDate);
			
			//일일별 누적 api 호출 수를 계산한다.
			for(var index = startDate; index <= endDate; index += 86400000){
				chartDate.push(index);
				temp1 = 0, temp2 = 0, temp3 = 0, temp4 = 0, temp5 = 0;
				//순차적으로 합계를 구하되, memo로 당일의 마지막 인덱스를 기억한다.
				for(var i = memo; i < length; i++){	
					switch(res.totalApiList[i].apiType){
						case 'article_list': temp1++; break;
						case 'article_detail': temp2++; break;
						case 'article_write': temp3++; break;
						case 'file_upload': temp4++; break;
						case 'file_download': temp5++; break;			
					}
					
					//하루 단위가 증가한 것을 감지한다.
					if(Date.parse(res.totalApiList[i].apiDate) > index){
						//마지막 날자의 인덱스는 계산하지 않고, 기억시켰다가 다시 반복될 때 계산된다. 
						memo = i;
						break;
					}
				}
				//날짜별로 api 호출 수를 저장한다.
				articleList.push(temp1);
				articleDetail.push(temp2);
				articleWrite.push(temp3);
				fileUpload.push(temp4);
				fileDownload.push(temp5);
			}
			//API 그래프 (BillBoard.js)
			var apiArticleList = ["게시판 목록"];
			var apiArticleDetail = ["게시글 내용"];
			var apiArticleWrite = ["게시글 쓰기"];
			var apiFileUpload = ["파일 다운로드"];
			var apiFileDownload = ["파일 업로드"];
			//그래프 이름과 값(배열) 붙이기
			Array.prototype.push.apply(apiArticleList, articleList); 
			Array.prototype.push.apply(apiArticleDetail, articleDetail); 
			Array.prototype.push.apply(apiArticleWrite, articleWrite); 
			Array.prototype.push.apply(apiFileUpload, fileUpload); 
			Array.prototype.push.apply(apiFileDownload, fileDownload); 
			
			var chart = bb.generate({
			  data: {
			    columns: [
			    ],
			    type: "bubble",
			    labels: true
			  },
			  bubble: {
			    maxR: 50
			  },
			  axis: {
				y: {
				  max : 170
				},
			    x: {
			      type: "category"
			    }
			  },
			  bindto: "#BubbleChart"
			});
			chart.categories([
				dateFormating(chartDate[0]) + "일", dateFormating(chartDate[1]) + "일", 
				dateFormating(chartDate[2]) + "일", dateFormating(chartDate[3]) + "일", 
				dateFormating(chartDate[4]) + "일", dateFormating(chartDate[5]) + "일", 
				dateFormating(chartDate[6]) + "일", dateFormating(chartDate[7]) + "일", 
				dateFormating(chartDate[8]) + "일", dateFormating(chartDate[9]) + "일", 
				dateFormating(chartDate[10]) + "일", dateFormating(chartDate[11]) + "일", 
				dateFormating(chartDate[12]) + "일", dateFormating(chartDate[13]) + "일", 
				dateFormating(chartDate[14]) + "일", dateFormating(chartDate[15]) + "일", 
				dateFormating(chartDate[16]) + "일", dateFormating(chartDate[17]) + "일"												
			]);

			chart.load({
				columns : [
					apiArticleList
					]
			});	
			setTimeout(function() {
				chart.load({
					columns: [
						apiArticleDetail
					]
				});
			}, 300);		
			setTimeout(function() {
				chart.load({
					columns: [
						apiArticleWrite
					]
				});
			}, 600);
			setTimeout(function() {
				chart.load({
					columns: [
						apiFileUpload
					]
				});
			}, 900);
			setTimeout(function() {
				chart.load({
					columns: [
						apiFileDownload
					]
				});
			}, 1200);
		
			//위에서 구한 일일별 API 호출 합계를, API 종류 별로 합계를 구한다.
			totalArticleList = getTotalData(articleList);
			totalArticleDetail = getTotalData(articleDetail);
			totalArticleWrite = getTotalData(articleWrite);
			totalFileUpload = getTotalData(fileUpload);
			totalFileDownload = getTotalData(fileDownload);
			totalApiCall = totalArticleList + totalArticleDetail + totalArticleWrite + totalFileUpload + totalFileDownload;
			
			function getTotalData(arr){
				let sum = 0;
				for(let index = 0; index < arr.length; index++){
					sum += arr[index];
				}
				return sum;
			}
			
		},
		error : function(err){
			alert("goApiGraphAjax error : " + err);
		}
	});
	location.hash = '/#/page:apigp';
}
function goApiGraphAjax2(){
	$("#BubbleChartHead").hide();
	$("#BubbleChart").hide();	
	$("#DonutChartHead").show();
	$("#DonutChart").show();
	var dounut = bb.generate({
		  data: {
		    columns: [							
		    ],
		    type: "donut",
		    onclick: function(d, i) {
			console.log("onclick", d, i);
		},
		    onover: function(d, i) {
			console.log("onover", d, i);
		},
		    onout: function(d, i) {
			console.log("onout", d, i);
		}
		  },
		  donut: {
		    title: "TOTAL : " + totalApiCall
		  },
		  bindto: "#DonutChart"
		});
	setTimeout(function() {
		dounut.load({
			columns: [
				["게시판 목록", totalArticleList]
			]
		});
	}, 100);	
	setTimeout(function() {
		dounut.load({
			columns: [
				["게시글 내용", totalArticleDetail]
			]
		});
	}, 300);	
	setTimeout(function() {
		dounut.load({
			columns: [
				["게시글 쓰기", totalArticleWrite]
			]
		});
	}, 600);	
	setTimeout(function() {
		dounut.load({
			columns: [
				["파일 다운로드", totalFileUpload]	
			]
		});
	}, 900);	
	setTimeout(function() {
		dounut.load({
			columns: [
				["파일 업로드", totalFileDownload]
			]
		});
	}, 1200);	
}
// 그래프의 x축 라벨을 ms단위에서 day단위로 변경한다.
function dateFormating(index){
    var noticeDate;
    var result = "";

	noticeDate = new Date(index) + "";
	result += noticeDate.substring(8, 10);
    return result;
}