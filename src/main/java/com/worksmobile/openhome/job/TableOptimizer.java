package com.worksmobile.openhome.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.worksmobile.openhome.dao.ApiCallDAO;

import lombok.extern.slf4j.Slf4j;
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class TableOptimizer extends QuartzJobBean {

	private ApiCallDAO dao;
	
        protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        	dao = (ApiCallDAO)ctx.getJobDetail().getJobDataMap().get("apiCallDAO");
        	log.info("Quartz Job Start");
		   if(dao == null) {
			   log.info("DAO 객체 에러 발생");
		   }
		   else {
			   //테이터 이전
			   dao.copyApiData("articleList");
			   dao.copyApiData("articleDetail");
			   dao.copyApiData("articleWrite");
			   dao.copyApiData("fileUpload");
			   dao.copyApiData("fileDownload");		
			   //기존 데이터 삭제
			   dao.clearApiData();
		   }
        }
} 