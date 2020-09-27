package com.tamecode.lesson20.autoconfigure;

import com.tamecode.lesson20.autoconfiguration.ConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liqc
 * @date 2020/9/24 10:28
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class ConfigAutoConfiguration {
}
