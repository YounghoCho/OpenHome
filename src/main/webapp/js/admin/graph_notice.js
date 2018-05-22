//트래픽 알림이 1번만 동작하게 한다
var flag1 = 1;
var flag2 = 1;
var flag3 = 1;

/*
 * 실시간 트래픽 관제 알고리즘
 * 
 * @Author : 조영호
 * 
 * 1. 4초에 한번씩 전체 트래픽 양을 검사한다.
 * 2. 당월 해당 날까지의 트래픽 양이 10k를 초과하면 경고 알람을 발생시킨다.
 * 3. 당월 해당 날까지의 트래픽 양이 100k를 초과하면 긴급  알람을 발생시킨다.
 * 
 */
function trafficTracking(){
	setInterval(function(){ 
		$.ajax({
			type : "GET",
			url : "api/traffic/trafficData",
			dataType : "json",
			success : function(res){
				var totalTraffic = 0;	
				for (index = 0; index < res.trafficData.length; index++){ //전체트래픽을 구한다.
					totalTraffic += res.trafficData[index].trafficContentLength;
				}
				if (totalTraffic > 7000000){	//7mb	
					if (totalTraffic > 10000000){	//10mb	
						notice1(flag1);
						//컨텐트를 누르면 flag를 바꾼다.
						flag1 = 0;
						flag2 = 1;

					}
					else{
						let flag = 1;
						notice2(flag2);
						flag2 = 0;
						flag1 = 1;
					}
				}
			},
			error : function(err){
				alert(err);
			}
		});
	}, 5000);
}

var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("closeNotice")[0];

function notice1(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Urgent");
	    $(".modal-body > p").html("<p>트래픽이 10000K를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "crimson");
	    $(".modal-footer").css("background-color", "crimson");
	}
}
function notice2(flag){
	if (flag != 0){
	    modal.style.display = "block";
	    $(".modal-header > h2").html("Warning");
	    $(".modal-body > p").html("<p>트래픽이 7000k를 초과했습니다.</p>");
	    $(".modal-header").css("background-color", "sandybrown");
	    $(".modal-footer").css("background-color", "sandybrown");
	}
}
function notice3(arr, flag){
	if (flag != 0){
		setTimeout(function(){
			modal.style.display = "block";
	        $(".modal-header > h2").html("Notice");
	        
	        var revDate, temp, noticeDate;
	        var result = "";
	        //alert(arr[0] + "," + arr[1]+","+arr[2]);
	        for(let index = 0; index < arr.length; index++){	        	
	        	revDate = (eval(arr[index]));
	        	temp = new Date(revDate);
	        	noticeDate = temp + "";
	        	result += noticeDate.substring(8, 10) + "일, ";
	        }
	 	       
	        $(".modal-body > p").html("<p>" + result + "트래픽이 비정상적으로 상승했습니다.</p>");
	        $(".modal-header").css("background-color", "darkorchid");
	        $(".modal-footer").css("background-color", "darkorchid");    	
		}, 5000);
	}
}
$(".modal-content").on("click", function(){
	drawDailyTrafficGraph();
	modal.style.display = "none";
});
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}