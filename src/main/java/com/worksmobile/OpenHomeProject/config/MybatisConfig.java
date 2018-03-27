package com.worksmobile.OpenHomeProject.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
/*import org.springframework.core.io.Resource;*/
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/*@MapperScan(value= {"com.worksmobile.OpenHomeProject.dao"})*/
public class MybatisConfig {

/*	//SqlSessionFactory ºó ¼±¾ð
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*mapper.xml"));
        return sessionFactory.getObject();
    }
	
	 @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	            sessionFactory.setDataSource(dataSource);
	            
	            Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*mapper.xml");
	            sessionFactory.setMapperLocations(res);
	            return sessionFactory.getObject();
	    }

	//SqlSessionTemplate ºó ¼±¾ð
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }*/
}
