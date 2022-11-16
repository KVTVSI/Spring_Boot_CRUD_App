package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.beanConfig;

import org.apache.commons.validator.EmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    EmailValidator emailValidator() {
        return EmailValidator.getInstance();
    }
}
