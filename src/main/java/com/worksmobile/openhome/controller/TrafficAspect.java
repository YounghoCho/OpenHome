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
 	//[읽기 트래픽 수집]
 	//filter로 reponse깐다, 왜냐면 컨트롤러에서 response header가 들어오지 않기때문이다.
 	//재호님이 보내주신거 테스트하기
 	//게시판 목록 조회, 게시글 목록 조회, 글 상세 조회, 파일 다운로드 = "read"
 	
 	//[쓰기 트래픽이란 수집]
 	//aop로 request깐다.
 	//여러개의 메소드를 pointcut으로 두는 방법을 찾는다. => ||
 	//5가지 메소드에서 쓰기 requeset가 발생한다. => "write 그래프"	
 	/*발견 1 
 	   : 네트워크를 보면, Query String Parameters로 URL에 붙어서 넘어가면 Request Header에 컨텐트로 들어가지 않아서 length가 0이다.
 	   Form data 형식으로 넘어오는 데이터는 Request헤더에 포함되므로 길이를 구할 수 있다..
 	*/
 	/*발견3
 	 * : AOP를 사용할때는 pointcut으로 @After("excution(~)"); 형식으로 접근이 가능합니다.
 	 *   그러나 AOP 대상이 되는 메소드가 여러개인 경우 다음과 같이 처리됩니다.
 	 *   	@After("execution(* com.worksmobile.openhome.controller.ArticleController.addArticleNum(..)) || " + 
  	 *		"execution(* com.worksmobile.openhome.controller.ArticleController.addArticle(..)) || " + 
	 *		"execution(* com.worksmobile.openhome.controller.ArticleController.modArticle(..)) || " +
	 *		"execution(* com.worksmobile.openhome.controller.AttachmentFileController.addFile(..)) || " +
	 *		"execution(* com.worksmobile.openhome.controller.AttachmentFileController.addFile(..)) || " +		
	 *		"execution(* com.worksmobile.openhome.controller.AttachmentFileController.addPhotoFile(..))" )
	 *   가독성이 떨어지는 방법이기 때문에 pointcut의 다른 표현식을 적용했습니다.
	 *   @After("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	 *   이렇게하면 PostMapping 어노테이션이 적용된 메소드들은 모두 AOP의 대상이 됩니다.
 	 */
 	
 	//[트래픽의 정의?]
 	//1. 트래픽을 구현한 이유 : OPENHOME을 사용하는 기업에게 트래픽 사용량에 따른 비용을 과금할 수 있는 위한 로직.
 	//2. 트래픽의 대상이 되는 데이터 : http header, api call 
 	//3. 트래픽을 수집한 방법 : aop, js => filter
 	//4. 트래픽을 표현한 방법 : highchar -> billboard
 	
 	@After("@annotation(org.springframework.web.bind.annotation.PostMapping)")
 	public void onAfterHandler(JoinPoint joinPoint) throws Throwable { 
		for (Object obj : joinPoint.getArgs()) {
			logger.info("annotation method path : " + joinPoint.getSignature());
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
            	HttpServletRequest req = (HttpServletRequest) obj;          
            	if(req.getContentLength() != -1) {
            		logger.info("req-C : " + req.getContentLength());
            		int trafficContentLength = req.getContentLength();
            		String trafficKind = "write";
            		dao.insertContentLength(trafficContentLength, trafficKind);          		
            	}           	
            }	 	
		}
	}//End After
 	
}//End Class
