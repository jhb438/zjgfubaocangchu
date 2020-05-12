package com.basic.zjgfbcc.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = {"com.basic.zjgfbcc.dao.postSql"},sqlSessionFactoryRef = "postSqlSqlSessionFactory")
public class SecondaryDataSources {
	
	//配置dataSource 【德鲁伊资源池】
    @Bean(name="postSqlDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.postsql")
    public DataSource testDataSource(){
    	return new DruidDataSource();
    }
    
    @Bean(name="postSqlSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("postSqlDataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/postSql/*.xml"));
        return bean.getObject();
    }
    
//    @Bean
//	@ConfigurationProperties(prefix ="mybatis.configuration")
//	public org.apache.ibatis.session.Configuration globalConfiguration(){
//		return new org.apache.ibatis.session.Configuration();
//	}
    
    @Bean(name = "postSqlTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("postSqlDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name="postSqlSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("postSqlSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
