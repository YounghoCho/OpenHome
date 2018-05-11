/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class OpenhomeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OpenhomeApplication.class, args);
	}
	@Bean 
	public TaskScheduler taskScheduler() { 
		return new ConcurrentTaskScheduler(); 
	} 
}
