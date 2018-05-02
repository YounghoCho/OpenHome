package com.worksmobile.openhome.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;

import com.worksmobile.openhome.dao.TrafficDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServeltResponseFilter implements Filter {
	/*
	 * 발견4
	 * : Response를 구할때는 AOP대신, Filter를 사용해보고자 했습니다.
	 *   그러나 필터에서 서비스 어노테이션을 사용해 DAO를 사용하고자 하니, NullPointerException에러가 발생했습니다.
	 *   이유인 즉, 일반적으로 필터는 서블릿 컨테이너에서 제어하기 때문에 DI로 생성된 객체들을 사용할 수 없었던 차이가 있었기 때문입니다.
	 *   그래서 문제를 해결하기 위해, 필터를 @Bean을 사용해 스프링 컨테이너에서 관리할 수 있도록 만들어 주었습니다.
	 */

 	@Resource(name="TrafficDAO")
	private TrafficDAO dao;
 	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);

		chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse)response) {			
		
			// 장애 발생 : DelegatingServletOutputStream 클래스를 찾을 수 없는 에러.
			// 처리 방법 : mock에 존재하는 DelegatingServletOutputStream 클래스를 같은 디렉토리에 복사.
			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), ps)
				);
			}	
			@Override
			public  PrintWriter getWriter() throws IOException {
				return new PrintWriter(new DelegatingServletOutputStream (new TeeOutputStream(super.getOutputStream(), ps))
				);
			}
			
		});
		
		if(baos.toByteArray().length != 0) {
			log.info("Servlet Response Body Length = " + baos.toByteArray().length);
			int trafficContentLength = baos.toByteArray().length;
    		String trafficKind = "read";
    		log.info("1 : " + trafficContentLength + ", 2 : " + trafficKind);
    		dao.insertContentLength(trafficContentLength, trafficKind);   
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init Filter");
	}
	@Override
	public void destroy() {
		log.info("destroy Filter");
	}
}
