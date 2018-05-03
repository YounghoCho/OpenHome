package com.worksmobile.openhome.config;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.worksmobile.openhome.filter.ServeltResponseFilter;

@Configuration
public class ConfigOpenhome {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*Mapper.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("1000MB");
		factory.setMaxRequestSize("1000MB");
		return factory.createMultipartConfig();
	}

	@Bean
	public ErrorPageFilter errorPageFilter() {
		return new ErrorPageFilter();
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	@Bean
	public ServeltResponseFilter serveltResponseFilter() {
		return new ServeltResponseFilter();
	}
	@Bean
	public FilterRegistrationBean<ServeltResponseFilter> filterRegistrationBean(ServeltResponseFilter serveltResponseFilter) {	//메소드 호출 없이 바로 매핑시킨다.
		FilterRegistrationBean<ServeltResponseFilter> registration = new FilterRegistrationBean<ServeltResponseFilter>(serveltResponseFilter);
		registration.addUrlPatterns("/api/article/*");
		registration.addUrlPatterns("/api/attachmentfile/*");
		 // 필터의 url 패턴을 api로 설정해줘야 한다. /*를 하게되면 (아무래도, 그냥 컨트롤러 /board /admin에서는 response가 없기때문에 빈화면이 나오는 것 같다).
		return registration;
	}
}
