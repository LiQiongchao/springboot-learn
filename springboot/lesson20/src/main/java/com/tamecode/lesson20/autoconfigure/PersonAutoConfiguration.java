package com.tamecode.lesson20.autoconfigure;

import com.tamecode.lesson20.domain.PersonProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ConditionalOnWebApplication
 *  当是web应用时，自动装配
 *
 * @ConditionalOnProperty(prefix = "person", name = "enable", havingValue = "true", matchIfMissing = true)
 *  当person.enable=true时，或者默认不配置时，会自动装配
 *
 * @EnableConfigurationProperties(PersonProperties.class)
 *  把属性PersonProperties引进来
 *
 * @author Liqc
 * @date 2020/9/23 17:31
 */
// 注入spring管理方式一， 方式二配置到META-INFO/spring.factories中
//@Configuration

@ConditionalOnWebApplication

// 自动配置方式一
//@EnableConfigurationProperties(PersonProperties.class)

@ConditionalOnProperty(prefix = "person", name = "enable", havingValue = "true", matchIfMissing = true)
public class PersonAutoConfiguration {
    // 自动配置方式二
    @ConfigurationProperties(prefix = "person")
    @Bean
    public PersonProperties personProperties() {
        return new PersonProperties();
    }
}
