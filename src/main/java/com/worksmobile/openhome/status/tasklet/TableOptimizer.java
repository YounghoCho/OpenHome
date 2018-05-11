package com.worksmobile.openhome.status.tasklet;

import javax.annotation.Resource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.worksmobile.openhome.dao.TrafficDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableOptimizer implements Tasklet {
	@Resource(name = "TrafficDAO")
	private TrafficDAO dao;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("execute Tasklet");
		return null;
	}
	
	
}
