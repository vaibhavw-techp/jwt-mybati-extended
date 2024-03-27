package com.demo.jwt.JwtMybatisApplication.config.usermanagementdatasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@MapperScan(value = "com.demo.jwt.JwtMybatisApplication",
        annotationClass= UserManagementSQLConnMapper.class,
        sqlSessionFactoryRef="sqlSessionFactory3")
@Configuration
public class UserManagementDatasourceConfig {

    @Bean(name = "dataSource3")
    @Primary
    @ConfigurationProperties(prefix = "spring.user-management")
    public org.apache.tomcat.jdbc.pool.DataSource dataSource3() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/user-management");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean(name = "sqlSessionFactory3")
    @Primary
    public SqlSessionFactory sqlSessionFactory3(@Qualifier("dataSource3") final DataSource dataSource3) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource3);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:user-mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate3")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate3(@Qualifier("sqlSessionFactory3") final SqlSessionFactory sqlSessionFactory3) {
        return new SqlSessionTemplate(sqlSessionFactory3);
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.user-management")
    public DataSourceProperties thirdDataSourceProperties() {
        return new DataSourceProperties();
    }

}



