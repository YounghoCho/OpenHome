package com.worksmobile.OpenHomeProject;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;



@SpringBootApplication
public class OpenHomeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenHomeProjectApplication.class, args);
	}
	
	//SqlSessionFactory �� ����
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*mapper.xml"));
        return sessionFactory.getObject();
    }
	
/*	 @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	            sessionFactory.setDataSource(dataSource);
	            
	            Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*mapper.xml");
	            sessionFactory.setMapperLocations(res);
	            return sessionFactory.getObject();
	    }*/

	//SqlSessionTemplate �� ����
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
	

}
