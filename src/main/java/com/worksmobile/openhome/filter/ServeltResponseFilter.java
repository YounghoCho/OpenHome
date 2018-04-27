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
import org.springframework.http.HttpStatus;

import com.worksmobile.openhome.dao.TrafficDAO;


public class ServeltResponseFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);
		
		chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse)response) {			
		
			//Issued : DelegatingServletOutputStream 클래스를 찾을 수 없는 에러.
			//Handled : mock에 존재하는 DelegatingServletOutputStream 클래스를 같은 디렉토리에 복사.
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
		
		if(((HttpServletResponse)response).getStatus() == HttpStatus.OK.value() && baos.toByteArray().length != 0) {
			System.out.println("Servlet Response Body Length = " + baos.toByteArray().length);

		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	@Override
	public void destroy() {

	}
}
