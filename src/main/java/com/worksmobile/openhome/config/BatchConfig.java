package com.worksmobile.openhome.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.worksmobile.openhome.status.tasklet.TableOptimizer;

import lombok.extern.slf4j.Slf4j; 
@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig { 
	@Autowired
	public JobBuilderFactory JobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory StepBuilderFactory;
	
	/*
	 * JobLuncher로 scheduler와 Batch를 연동시킨다.
	 * [Scheduler 1.Trigger 2.Job] -> JobLuncher -> [Batch 1.job 2.step 3.ItemReader, Processor, Writer] 
	 */
	@Autowired
	private SimpleJobLauncher JobLauncher; 
	
	//	@Resource(name = "TrafficDAO")
	//	private TrafficDAO dao; 

	// 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행하기
	//@Scheduled(initialDelay = 1000, fixedDelay = 1000) 
	@Scheduled(cron = "0 0 0 * * *") 
	public void execute() throws Exception{
		log.info("Batch Started by Scheduler");
		JobParameters param = new JobParametersBuilder().addString("JobID",
				String.valueOf(System.currentTimeMillis())).toJobParameters();
		JobExecution execution = JobLauncher.run(firstJob(), param);
		log.info("Batch Finished");
//		dao.insertContentLength(7, "check");
//		System.out.println("test");
	} 

	private Job firstJob() {
		return JobBuilderFactory.get("Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.build();		
	}
	private Step firstStep() {
		return StepBuilderFactory.get("Step")
				.tasklet(new TableOptimizer())
				.build();
	}
}