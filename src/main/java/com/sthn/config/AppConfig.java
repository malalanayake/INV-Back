package com.sthn.config;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    Logger log = Logger.getLogger(this.getClass());
    protected String APP_ENV;
    protected final String DATABASE_USERNAME;
    protected final String DATABASE_PASSWORD;
    protected final String DATASOURCE_URL;

    @Autowired
    CamelContext camelContext;

    public AppConfig() {
        APP_ENV = System.getenv().get("APP_ENV");
        DATABASE_USERNAME = System.getenv().get("DATABASE_USERNAME");
        DATABASE_PASSWORD = System.getenv().get("DATABASE_PASSWORD");
        DATASOURCE_URL = System.getenv().get("DATASOURCE_URL");
    }

    @Bean
    public Exchange getExchange() {
        return new DefaultExchange(camelContext);
    }

    @Bean
    public Message getMessage() {
        return new DefaultMessage();
    }


    @Bean
    public DataSource dataSource() {
        log.info("[START:Set DataSource]");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(DATASOURCE_URL);
        basicDataSource.setUsername(DATABASE_USERNAME);
        basicDataSource.setPassword(DATABASE_PASSWORD);
        log.info("[END:Set DataSource]");
        return basicDataSource;

    }
}
