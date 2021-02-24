package com.hjg.spring.dynamic.conf;

import com.hjg.spring.dynamic.model.Phone;
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
    Phone nokia() {
        Phone nokia = new Phone();
        nokia.setName("Nokia");
        return nokia;
    }

    @Bean
    PhoneBeanFactoryPostProcessor phoneBeanFactoryPostProcessor() {
        return new PhoneBeanFactoryPostProcessor();
    }

    @Bean
    PhoneBeanDefPostProcessor phoneBeanDefPostProcessor() {
        return new PhoneBeanDefPostProcessor();
    }
}
