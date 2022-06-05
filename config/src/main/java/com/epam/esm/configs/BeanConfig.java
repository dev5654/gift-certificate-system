package com.epam.esm.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Bean config
 */

@Configuration
//@PropertySource(value = "classpath:/application-prod.properties", ignoreResourceNotFound = true)
//@Profile(value = {"prod", "dev"})
public class BeanConfig {

//    @Value("${url}")
//    private String url;
//    @Value("${username}")
//    private String username;
//    @Value("${password}")
//    private String password;
//    @Value("${driver}")
//    private String driver;
//    @Value("${size}")
//    private int size;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/certificate-system");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("123");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
