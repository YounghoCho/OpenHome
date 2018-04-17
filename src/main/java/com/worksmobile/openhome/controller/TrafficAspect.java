package com.worksmobile.openhome.controller;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Aspect
@Component
public class TrafficAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(TrafficAspect.class);
	
	@Around("execution(* com.worksmobile.openhome.controller.*.*(..))")
	public Object onBeforeHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		/* AOP 출력
		Signature signature = joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();	//Model 값 확인		  
		System.out.println("name : " + signature.getName());	//Method 이름	  
		for(int i=0; i < args.length; i++){
			System.out.println("args[" + i + "] : " + args[i].toString());	//파라미터 목록
		}
		*/
		logger.info("===============Start AOP================");
		Object result = null;
		for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) obj;

                // Doing...
                logger.info(request.getParameter("boardCount"));
            }
        }
		
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }
   

        return result;
	}
}
