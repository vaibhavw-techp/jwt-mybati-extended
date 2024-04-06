package com.demo.jwt.JwtMybatisApplication.config;

import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.HostelRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.demo.jwt.JwtMybatisApplication",
        annotationClass= MessManagementSQLConnMapper.class,
        sqlSessionFactoryRef="sqlSessionFactory2")
@Configuration
@EnableTransactionManagement
public class MessManagementDatasourceConfig {

    @Bean(name = "dataSource2")
    @Primary
    @ConfigurationProperties(prefix = "spring.management")
    public org.apache.tomcat.jdbc.pool.DataSource dataSource2() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/management");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean(name = "sqlSessionFactory2")
    @Primary
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") final DataSource dataSource2) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource2);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate2")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") final SqlSessionFactory sqlSessionFactory2) {
        return new SqlSessionTemplate(sqlSessionFactory2);
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.management")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "messManagement")
    public DataSourceTransactionManager schoolDatabaseTransactionManager() {
        return new DataSourceTransactionManager(dataSource2());
    }

}
