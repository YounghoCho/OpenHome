/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.aop;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.dao.TrafficDAO;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TrafficAspect {
	 
 	@Resource(name="TrafficDAO")
	private TrafficDAO dao;

 	@After("@annotation(com.worksmobile.openhome.controller.annotaion.GetWriteTraffic)")
 	public void onAfterHandler(JoinPoint joinPoint) throws Throwable { 
		for (Object obj : joinPoint.getArgs()) {
			log.info("annotation method path : " + joinPoint.getSignature());
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
            	HttpServletRequest req = (HttpServletRequest) obj;          
            	if(req.getContentLength() != -1) {
            		log.info("req-C : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		//dao.insertContentLength(trafficContentLength, trafficKind);          		
            	}           	
            }	 	
		}
	}//End After
}//End Class
