/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
 	
 	//HttpServletRequest 객체의 Content-Length를 구한다.
 	@After("@annotation(com.worksmobile.openhome.controller.annotaion.GetWriteTraffic)")
 	public void onAfterHandler(JoinPoint joinPoint) throws Throwable { 

 		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
				log.info("annotation method path : " + joinPoint.getSignature());
				HttpServletRequest req = (HttpServletRequest) obj;          
            	if (req.getContentLength() != -1) {
            		log.info("req-C : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		//dao.insertContentLength(trafficContentLength, trafficKind);          		
            	}           	
            }	 	
		}
	}//End After
 
 	/*
 	 * [API 호출 트래픽 Level 5]
 	 * 1 : 게시글 목록 호출 (Read)
 	 * 2 : 게시글 내용 호출 (Read)
 	 * 3 : 게시글 쓰기 호출 (Write)
 	 * 4 : 파일 업로드 호출 (Write)
 	 * 5 : 파일 다운로드 호출 (Read)
 	 */
 	//Level 3
 	@After("execution(* com.worksmobile.openhome.controller.ArticleController.addArticleNum(..)) ||"
 			+ "execution(* com.worksmobile.openhome.controller.ArticleController.addArticle(..)) ||"
 			+ "execution(* com.worksmobile.openhome.controller.ArticleController.modArticle(..))")
 	public void onAfterApiHandler(JoinPoint joinPoint) throws Throwable { 

 		for (Object obj : joinPoint.getArgs()) {
			log.info("#Traffic : annotation method path : " + joinPoint.getSignature());

			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
            	HttpServletRequest req = (HttpServletRequest) obj;          
            	if (req.getContentLength() != -1) {
            		log.info("#Content-Length(Request Write) : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		//dao.insertContentLength(trafficContentLength, trafficKind);          		
            	}           	
            }	 	
		}
	}//End After
}//End Class
