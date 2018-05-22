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
public class ResponseTrafficFilter implements Filter {

 	@Resource(name="TrafficDAO")
	private TrafficDAO dao;
 	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);

		chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse)response) { 
			//doFilter : 사용자와 서버 사이에 위치하여 호출되는 시점부터 필터가 동작한다
			//Wrapper : 필터가 요청, 응답을 변경한 결과를 저장할 클래스
		
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
