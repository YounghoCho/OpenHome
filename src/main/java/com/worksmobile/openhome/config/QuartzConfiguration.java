package com.worksmobile.openhome.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.worksmobile.openhome.job.TableOptimizer;
@Configuration 
@ComponentScan("com.worksmobile.openhome.job") 
public class QuartzConfiguration {
	/*
	 * 간단한 job을 사용할때는
	 * job을 정의하는 MethodInvokingJobDetailFactoryBean과
	 * 스케줄링에 필요한 SimpleTriggerFactoryBean을 정의해주고
	 * 맨 아래 스케줄 빈에 등록해준다.
	 * 
	 * 본인은 조금 더 세밀한 조작이 필요하기 때문에
	 * JobDetailFactoryBean과 CronTriggerFactoryBean를 사용한다.
	 */
	
	//복잡한 job이 필요한경우에 선언한다.(job이름 group이름 설정)
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(TableOptimizer.class);
		Map<String,Object> map = new HashMap<String,Object>();
		factory.setJobDataAsMap(map);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}
	//복잡한 job은 Cron을 사용해 스케줄한다 (매 분) 
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression("0/10 * * * * ? *");
		return stFactory;
	}
	//두가지 트리거를 등록한다.
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}
} 