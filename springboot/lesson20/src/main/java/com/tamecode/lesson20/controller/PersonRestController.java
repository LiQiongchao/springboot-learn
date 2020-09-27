package com.tamecode.lesson20.controller;

import com.tamecode.lesson20.domain.PersonProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liqc
 * @date 2020/9/23 17:46
 */
@RestController
public class PersonRestController {

    /**
     * 使用构造器注入，会比通常的注入要早
     */
    private final PersonProperties personProperties;

    public PersonRestController(PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

    @GetMapping("person")
    public PersonProperties get() {
        return personProperties;
    }

}
