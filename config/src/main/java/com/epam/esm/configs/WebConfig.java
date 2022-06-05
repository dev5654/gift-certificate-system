package com.epam.esm.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Web config
 */

@Configuration
@ComponentScan("com.epam.esm")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
}
