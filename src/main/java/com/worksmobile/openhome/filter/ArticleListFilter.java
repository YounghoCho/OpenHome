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

import com.worksmobile.openhome.dao.ApiCallDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleListFilter implements Filter {

 	@Resource(name="ApiCallDAO")
	private ApiCallDAO dao;
 	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);

		chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse)response) { 
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
			log.info("Leve 1 발생");
			dao.insertApiCall("articleList");
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
