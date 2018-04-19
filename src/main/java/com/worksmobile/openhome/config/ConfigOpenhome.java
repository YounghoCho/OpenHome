package com.worksmobile.openhome.config;

import java.nio.charset.Charset;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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
	    public HttpMessageConverter<String> responseBodyConverter() {
	        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	    }
	    
	    @Bean
	    public CharacterEncodingFilter characterEncodingFilter() {
	    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	        characterEncodingFilter.setEncoding("UTF-8");
	        characterEncodingFilter.setForceEncoding(true);
	        return characterEncodingFilter;
	    }
	    
	    @Bean
	    public InternalResourceViewResolver resolver() {
	       InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	       resolver.setViewClass(JstlView.class);
	       resolver.setPrefix("/WEB-INF/views/");
	       resolver.setSuffix(".jsp");
	       return resolver;
	    }
}
