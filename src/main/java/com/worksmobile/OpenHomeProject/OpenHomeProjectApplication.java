package com.worksmobile.OpenHomeProject;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;



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

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// Customize the application or call application.sources(...) to add sources
		// Since our example is itself a @Configuration class (via @SpringBootApplication)
		// we actually don't need to override this method.
		return application;
	}
    
}
