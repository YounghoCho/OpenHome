package com.worksmobile.openhome.status.scheduler;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.worksmobile.openhome.dao.ApiCallDAO;
import com.worksmobile.openhome.dao.TrafficDAO;

import lombok.extern.slf4j.Slf4j;
@Component 
@Slf4j
public class TableOptimizer{

	@Resource(name = "TrafficDAO")
	private TrafficDAO trafficDAO;
	
	@Resource(name = "ApiCallDAO")
	private ApiCallDAO apiCallDAO;
	
	// 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다. 
	//@Scheduled(initialDelay = 1000, fixedDelay = 1000) 
	@Scheduled(initialDelay = 1000, fixedDelay = 9999000)//(cron = "0 0 0 * * *") 
	public void otherJob() { 
		/* 테이블 최적화 
		 * 
		 * 1.매일 24시에 트래픽과 API호출 테이블을 정리해 DB용량을 최적화 시킨다.
		 * 2. traffic, api_call 테이블들을 각각 Type별로 더한다.
		 * 3. traffic_memo, api_call_memo 테이블에 일일별 총 합계를 기록한다.
		 * 4. traffic. api_call 테이블의 데이터를 정리ㅇ한다.
		 */
		
		//기록
		trafficDAO.copyTrafficData("read");
		trafficDAO.copyTrafficData("write");
		trafficDAO.copyTrafficData("fileUpload");
		trafficDAO.copyTrafficData("fileDownload");	
		apiCallDAO.copyApiData("articleList");
		apiCallDAO.copyApiData("articleDetail");
		apiCallDAO.copyApiData("articleWrite");
		apiCallDAO.copyApiData("fileUpload");
		apiCallDAO.copyApiData("fileDownload");
		
		//삭제
		trafficDAO.clearTrafficData();
		apiCallDAO.clearApiData();
	} 
}