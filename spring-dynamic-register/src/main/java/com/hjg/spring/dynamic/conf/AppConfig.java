package com.hjg.spring.dynamic.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/22
 */
@EnableOtherPhone(phones = {"Vivo", "Lenovo"})
@Configuration
public class AppConfig {

    @Bean
    PhoneBeanFactoryPostProcessor phoneBeanFactoryPostProcessor() {
        return new PhoneBeanFactoryPostProcessor();
    }

    @Bean
    PhoneBeanDefPostProcessor phoneBeanDefPostProcessor() {
        return new PhoneBeanDefPostProcessor();
    }
}
