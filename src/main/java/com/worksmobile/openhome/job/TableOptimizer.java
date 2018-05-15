package com.worksmobile.openhome.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.worksmobile.openhome.dao.ApiCallDAO;

import lombok.extern.slf4j.Slf4j;
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class TableOptimizer extends QuartzJobBean {
	@Autowired
	private ApiCallDAO apiCallDao;
	
        protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {

		   if(apiCallDao == null) 
			   log.info("null입니다"); 
        }
} 