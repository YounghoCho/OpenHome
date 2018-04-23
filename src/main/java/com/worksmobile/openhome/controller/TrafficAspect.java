/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.dao.TrafficDAO;

@Aspect
@Component
public class TrafficAspect {
	
 	private static final Logger logger = LoggerFactory.getLogger(TrafficAspect.class);
 
 	@Resource(name="TrafficDAO")
	private TrafficDAO dao;
 	
	@After("execution(* com.worksmobile.openhome.controller.*.*(..))")
	//method를 실행하려면 JoinPoint대신 ProceedingJoinPoint를 사용하고, joinPoint.proceed()로 실행한다.
	public void onBeforeHandler(JoinPoint joinPoint) throws Throwable { 
	
		logger.info("===============Start AOP================");
		for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {

            	HttpServletRequest req = (HttpServletRequest) obj;
        
            	if(req.getContentLength() != -1) {
            		logger.info("req-C : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		dao.insertContentLength(trafficContentLength, trafficKind);          		
            	}
            	
                //1. AOP를 사용해 HTTP request에서 Content-len를 추출한다. 단 -1은 예외처리. 
                //2. 컨트롤러에서 HTTP response Header에서 content-length를 확인할 수 없으므로,
                //   데이터를 처리하는 Ajax 부분과, HttpUrlConnection 방식으로 응답 길이를 얻는다.
                //3. Traffic 데이터를 삽입하기. 단, dao를 가져와서 삽입한다.
                //4. 게시판 속도를 올리기 위해 @Async로 AOP를 비동기 처리한다. 단, 클래스를 따로 빼야 동작한다고 하신다.                
            }
        }		 
		
	}
}
