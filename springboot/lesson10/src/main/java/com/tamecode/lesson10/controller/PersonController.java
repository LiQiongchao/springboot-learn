package com.tamecode.lesson10.controller;

import com.tamecode.lesson10.entity.Person;
import com.tamecode.lesson10.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * person 控制类
 *
 * @author Liqc
 * @date 2020/9/18 13:48
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "person", produces = "application/json")
    public Person save(@RequestBody Person person) {
//        personRepository.savePersonFromCache(person);
        personRepository.savePersonFromRedis(person);
        return person;
    }

    @GetMapping(value = "person/{id}", produces = "application/json")
    public Person get(@PathVariable String id) {
//        return personRepository.findPersonFromCache(id);
        return personRepository.findPersonFromRedis(id);
    }
}
