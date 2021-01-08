package com.hjg.spring.spel.model.conf;

import com.hjg.spring.spel.model.model.Inventor;
import com.hjg.spring.spel.model.model.Society;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/1/8
 */
@Configuration
public class SpelConfig {

    @Bean
    Inventor inventor() {
        Inventor inventor = new Inventor();
        inventor.setName("Jack");
        inventor.setNationality("USA");
        return inventor;
    }

    /**
     * 对于这样的使用，根对象是applicationContext
     * @param name
     * @return
     */
    @Bean
    Society society(@Value("#{inventor.name}") String name) {
        Society society = new Society();
        society.setName(name);
        return society;
    }
}
