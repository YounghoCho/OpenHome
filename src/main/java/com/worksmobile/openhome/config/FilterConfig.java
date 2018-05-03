package com.worksmobile.openhome.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.worksmobile.openhome.filter.ArticleDetailFilter;
import com.worksmobile.openhome.filter.ArticleListFilter;
import com.worksmobile.openhome.filter.ResponseTrafficFilter;

@Configuration
public class FilterConfig {
	@Bean
	public ResponseTrafficFilter responseTrafficFilter() {
		return new ResponseTrafficFilter();
	}
	@Bean
	public ArticleListFilter articleListFilter() {
		return new ArticleListFilter();
	}
	@Bean
	public ArticleDetailFilter articleDetailFilter() {
		return new ArticleDetailFilter();
	}	
	/* Traffic */
	@Bean
	public FilterRegistrationBean<ResponseTrafficFilter> trafficFilterRegBean(ResponseTrafficFilter responseTrafficFilter) {	//메소드 호출 없이 바로 매핑시킨다.
		FilterRegistrationBean<ResponseTrafficFilter> registration = new FilterRegistrationBean<ResponseTrafficFilter>(responseTrafficFilter);
		registration.addUrlPatterns("/api/article/*");
		registration.addUrlPatterns("/api/attachmentfile/*");
		 // 필터의 url 패턴을 api로 설정해줘야 한다. /*를 하게되면 (아무래도, 그냥 컨트롤러 /board /admin에서는 response가 없기때문에 빈화면이 나오는 것 같다).
		return registration;
	}
	
	/* API */
	//Level 1 : ArticleList
	@Bean
	public FilterRegistrationBean<ArticleListFilter> articleListFilterRegBean(ArticleListFilter articleListFilter) {
		FilterRegistrationBean<ArticleListFilter> registration = new FilterRegistrationBean<ArticleListFilter>(articleListFilter);
		registration.addUrlPatterns("/api/article/homeList");
		registration.addUrlPatterns("/api/article/articleList");
		return registration;
	}
	//Level 2 : ArticleDetail
	@Bean
	public FilterRegistrationBean<ArticleDetailFilter> articleDetailFilterRegBean(ArticleDetailFilter articleDetailFilter) {
		FilterRegistrationBean<ArticleDetailFilter> registration = new FilterRegistrationBean<ArticleDetailFilter>(articleDetailFilter);
		registration.addUrlPatterns("/api/article/articleDetails");
		return registration;
	}	
}
