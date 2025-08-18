package com.example.chap9_boot;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.example.chap9_boot.repos", "com.example.chap9_boot.services"})
@EnableTransactionManagement
public class TransactionalConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    @ConfigurationProperties("spring.jpa.properties")
    public Properties jpaProperties() {
        return new Properties();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.chap9_boot.entities");
        sessionFactory.setHibernateProperties(jpaProperties());
        return sessionFactory;
    }

}