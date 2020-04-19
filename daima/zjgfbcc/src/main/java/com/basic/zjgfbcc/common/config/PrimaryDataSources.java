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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Primary
@Configuration
@MapperScan(basePackages = {"com.basic.zjgfbcc.dao.mysql"},sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class PrimaryDataSources {
	
	//配置dataSource 【德鲁伊资源池】
	@Primary
    @Bean(name="mysqlDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.mysql")
    public DataSource testDataSource(){
        return new DruidDataSource();
    }
	@Primary
    @Bean(name="mysqlSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource,org.apache.ibatis.session.Configuration configuration)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
        return bean.getObject();
    }
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix ="mybatis.configuration")
	public org.apache.ibatis.session.Configuration globalConfiguration(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	
	@Primary
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mysqlDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
	@Primary
    @Bean(name="mysqlSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
