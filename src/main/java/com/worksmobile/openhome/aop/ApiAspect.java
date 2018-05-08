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

import com.worksmobile.openhome.dao.ApiCallDAO;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ApiAspect {
	 
 	@Resource(name="ApiCallDAO")
	private ApiCallDAO dao;

 	/*
 	 * [API 호출 트래픽 Level 5 Steps]
 	 * Level 1 : 게시글 목록 호출 (article_list)
 	 * Level 2 : 게시글 내용 호출 (article_detail)
 	 * Level 3 : 게시글 쓰기 호출 (article_write) 
 	 * Level 4 : 파일 업로드 호출 (file_upload)
 	 * Level 5 : 파일 다운로드 호출 (file_download)
 	 */
 	
 	//Level 3
 	@After("@annotation(com.worksmobile.openhome.controller.annotaion.GetArticleWriteApiCall)")
	public void articleWriteHandler(JoinPoint joinPoint) throws Throwable { 

 		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
				log.info("Leve 3 발생 : annotation method path : " + joinPoint.getSignature());
				dao.insertApiCall("apiLevel3");
			}
		}
	}
 	//Level 4
 	@After("@annotation(com.worksmobile.openhome.controller.annotaion.GetFileUploadApiCall)")
 	public void fileUploadHandler(JoinPoint joinPoint) throws Throwable { 

 		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
				log.info("Leve 4 발생 : annotation method path : " + joinPoint.getSignature());
				dao.insertApiCall("apiLevel4");
			}
		}
	}
 	
}//End Class
