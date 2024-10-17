package com.hjcompany.connectmysql_ex.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class DatabaseConfiguration {
   
   @Autowired
   private ApplicationContext applicationContext;

   /**
     * SqlSessionFactory 빈 생성
     * 
     * 
     * @param dataSource 데이터베이스 연결을 위한 DataSource 객체
     * @return SqlSessionFactoryBean 객체
     * @throws Exception 예외 처리
     */

     
   @Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

   @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
      
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      System.out.println("=========  1  ================================");

		sqlSessionFactoryBean.setDataSource(dataSource);
      System.out.println("=========  2  ================================");

		sqlSessionFactoryBean.setMapperLocations( applicationContext.getResources("classpath:/mapper/*.xml"));
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
      System.out.println("=========  3  ================================");

		sqlSessionFactoryBean.setTypeAliasesPackage("com.hjcompany.connectmysql_ex.model");
      System.out.println("=========  4  ================================");
      
		return sqlSessionFactoryBean.getObject();
    }
   
     /**
     * SqlSessionTemplate 빈 생성
     * 
     * 
     * @param sqlSessionFactory SqlSessionFactory 객체
     * @return SqlSessionTemplate 인스턴스
     */

     
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {

      System.out.println("==================== 77 ========================");

      return new SqlSessionTemplate(sqlSessionFactory); // SqlSessionTemplate 반환
    }
        
}
