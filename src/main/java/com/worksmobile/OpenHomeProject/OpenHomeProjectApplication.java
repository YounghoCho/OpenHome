package com.worksmobile.OpenHomeProject;


import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;



@SpringBootApplication
public class OpenHomeProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OpenHomeProjectApplication.class, args);
	}
	
	//SqlSessionFactory �� ���� (Mybatis�� Spring�� �����Ѵ�)
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*mapper.xml"));
        return sessionFactory.getObject();
    }

	//SqlSessionTemplate �� ���� (SqlSesstion�� ���� : �ѹ� �����ϸ� ���α��� ����, Ŀ��, �ѹ� �� �� �ִ�)
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }    
    //
    @Bean
    public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize("1000MB");
	    factory.setMaxRequestSize("1000MB");
	    return factory.createMultipartConfig();
    }

    //MultipartResolver �� ����
/*    @Bean
    public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(999000000);
	    return multipartResolver;
    }*/
    
    @Bean
    public MultipartResolver multipartResolver() {

        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();

        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10

        return multipartResolver;

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
    
    
}
