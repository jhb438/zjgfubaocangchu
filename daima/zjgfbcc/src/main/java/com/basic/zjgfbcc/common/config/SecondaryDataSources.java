//package com.basic.zjgfbcc.common.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration
//@MapperScan(basePackages = {"com.basic.zjgfbcc.dao.sqlServer"},sqlSessionFactoryRef = "sqlServerSqlSessionFactory")
//public class SecondaryDataSources {
//	
//	//配置dataSource 【德鲁伊资源池】
//    @Bean(name="sqlServerDataSource")
//    @ConfigurationProperties(prefix ="spring.datasource.sqlserver")
//    public DataSource testDataSource(){
//    	return new DruidDataSource();
//    }
//    
//    @Bean(name="sqlServerSqlSessionFactory")
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("sqlServerDataSource") DataSource dataSource)throws Exception{
//        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
////        bean.setConfiguration(configuration);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sqlServer/*.xml"));
//        return bean.getObject();
//    }
//    
////    @Bean
////	@ConfigurationProperties(prefix ="mybatis.configuration")
////	public org.apache.ibatis.session.Configuration globalConfiguration(){
////		return new org.apache.ibatis.session.Configuration();
////	}
//    
//    @Bean(name = "sqlServerTransactionManager")
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("sqlServerDataSource")DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//    @Bean(name="sqlServerSqlSessionTemplate")
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlServerSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
