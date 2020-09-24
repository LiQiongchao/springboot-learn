package com.tamecode.lesson20.controller;

import com.tamecode.lesson20.autoconfiguration.ConfigProperties;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liqc
 * @date 2020/9/24 10:30
 */
@Data
@RestController
public class PropertiesRestController {

    private final ConfigProperties configProperties;

    @GetMapping("config")
    public Object getConfig() {
        return configProperties;
    }

}
