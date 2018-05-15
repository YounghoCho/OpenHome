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
		 * 24시마다 트래픽, API 테이블의 합계를 게산해서 기록한다.
		 * 기록이 완료되면 기존 테이블의 내용을 삭제한다.
		 */
		
		//기록
//		trafficDAO.copyTrafficData("read");
//		trafficDAO.copyTrafficData("write");
//		trafficDAO.copyTrafficData("fileUpload");
//		trafficDAO.copyTrafficData("fileDownload");	
//		apiCallDAO.copyApiData("articleList");
//		apiCallDAO.copyApiData("articleDetail");
//		apiCallDAO.copyApiData("articleWrite");
//		apiCallDAO.copyApiData("fileUpload");
//		apiCallDAO.copyApiData("fileDownload");
//		
//		//삭제
//		trafficDAO.clearTrafficData();
//		apiCallDAO.clearApiData();
	} 
}