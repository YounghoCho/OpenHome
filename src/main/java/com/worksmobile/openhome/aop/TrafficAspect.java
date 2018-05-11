/*
 * 트래픽은 "API 호출 횟수"와 "HTTP Header Content-length" 두 가지로 정의한다.
 * AOP(Aspect Oriented Programming)를 사용해 트래픽 데이터를 수집한다.
 * 
 * @Author : 조영호
 */
package com.worksmobile.openhome.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.dao.ApiCallDAO;
import com.worksmobile.openhome.dao.TrafficDAO;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TrafficAspect {

 	@Resource(name="ApiCallDAO")
	private ApiCallDAO apiCallDao;

 	@Resource(name="TrafficDAO")
	private TrafficDAO trafficDao;
 	 	
 	/*
 	 * 클라이언트의 API 호출을 다섯단계의 Level로 관리한다.
 	 * 
 	 * Level 1 : 게시글 목록
 	 * Level 2 : 게시글 내용 
 	 * Level 3 : 게시글 쓰기 
 	 * Level 4 : 파일 업로드 
 	 * Level 5 : 파일 다운로드 
 	 */ 
 	 
 	@After("@annotation(com.worksmobile.openhome.controller.annotaion.GetTrafficData)")
 	@Async
 	//어노테이션이 적용된 메소드가 실행 된 후에, API 호출 횟수와 트래픽 크기를 저장한다. 
 	//아래 메소드에서는 level3과 level4를 다룬다.
 	public void onAfterHandler(JoinPoint joinPoint) throws Throwable { 

 		for (Object obj : joinPoint.getArgs()) { 						
 			if (obj instanceof HttpServletRequest) {					
 				log.info("Level 3 호출, 메소드 경로 : " + joinPoint.getSignature());
 				apiCallDao.insertApiCall("apiLevel3");
 		
 				HttpServletRequest req = (HttpServletRequest) obj;                      		
				if (req.getContentLength() != -1) {
            		log.info("Level 3 크기 : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		trafficDao.insertContentLength(trafficContentLength, trafficKind);          		
            	}      
            }
 			else if (obj instanceof MultipartHttpServletRequest) {			
				log.info("Leve 4 호출, 메소드 경로 : " + joinPoint.getSignature());
				apiCallDao.insertApiCall("apiLevel4");
				
				HttpServletRequest req = (HttpServletRequest) obj;                     	
				if (req.getContentLength() != -1) {
            		log.info("Level 4 크기 : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "fileUpload";
            		trafficDao.insertContentLength(trafficContentLength, trafficKind);          		
            	}           	
            }
		} //End for
 		
 	} //End Method
}