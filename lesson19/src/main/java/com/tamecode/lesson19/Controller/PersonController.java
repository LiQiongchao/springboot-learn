package com.tamecode.lesson19.Controller;

import com.tamecode.lesson19.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liqc
 * @date 2020/9/25 16:51
 */
@RequiredArgsConstructor
@RestController
public class PersonController {

    private final Person person;

    @GetMapping
    public Person person() {
        return person;
    }



}
