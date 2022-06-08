package com.epam.esm.configs;

import com.epam.esm.DAO.gift_certificate.GiftCertificateDAOImpl;
import com.epam.esm.DAO.tag.TagDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Bean config
 **/


@Configuration
//@PropertySource(value = "classpath:application-test.properties", ignoreResourceNotFound = true)
public class BeanConfig {

//    @Value("${url}")
//    private String url;
//    @Value("${username}")
//    private String username;
//    @Value("${password}")
//    private String password;
//    @Value("${driver}")
//    private String driver;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.h2.Driver");
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public MapSqlParameterSource parameterSource() {
        return new MapSqlParameterSource();
    }

    @Bean
    public GiftCertificateDAOImpl giftCertificateDAO(NamedParameterJdbcTemplate jdbcTemplate, MapSqlParameterSource parameterSource) {
        return new GiftCertificateDAOImpl(jdbcTemplate, parameterSource);
    }


    @Bean
    public TagDAOImpl tagDAO(NamedParameterJdbcTemplate jdbcTemplate, MapSqlParameterSource parameterSource) {
        return new TagDAOImpl(jdbcTemplate, parameterSource);
    }
}
