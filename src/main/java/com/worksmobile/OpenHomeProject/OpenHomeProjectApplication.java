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
	
	//SqlSessionFactory 빈 선언 (Mybatis와 Spring을 연동한다)
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*mapper.xml"));
        return sessionFactory.getObject();
    }

	//SqlSessionTemplate 빈 선언 (SqlSesstion을 생성 : 한번 생성하면 매핑구문 실행, 커밋, 롤백 할 수 있다)
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
