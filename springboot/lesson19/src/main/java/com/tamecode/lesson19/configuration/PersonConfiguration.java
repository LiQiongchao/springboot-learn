package com.tamecode.lesson19.configuration;

import com.tamecode.lesson19.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link Person} Bean配置
 *
 * @author Liqc
 * @date 2020/9/25 16:49
 */
@Configuration
public class PersonConfiguration {

    @Bean("primaryPerson")
    @Primary
    public Person person() {
        Person person = new Person();
        person.setId(1L);
        person.setName("gaga");
        person.setAge(23);
        return person;
    }

}
