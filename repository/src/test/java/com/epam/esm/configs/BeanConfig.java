package com.epam.esm.configs;

import com.epam.esm.DAO.gift_certificate.GiftCertificateDAOImpl;
import com.epam.esm.DAO.tag.TagDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Bean config
 */

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
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public GiftCertificateDAOImpl giftCertificateDAO(JdbcTemplate jdbcTemplate) {
        return new GiftCertificateDAOImpl(jdbcTemplate);
    }

    @Bean
    public TagDAOImpl tagDAO(JdbcTemplate jdbcTemplate) {
        return new TagDAOImpl(jdbcTemplate);
    }


}
