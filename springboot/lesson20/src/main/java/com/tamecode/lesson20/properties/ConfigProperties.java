package com.tamecode.lesson20.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author Liqc
 * @date 2020/9/24 10:26
 */
@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

    private List list;
    private Map<String, String> map1;
    private Map<String, String> map2;

}
