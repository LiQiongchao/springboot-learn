package com.tamecode.lesson10.repository;

import com.tamecode.lesson10.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liqc
 * @date 2020/9/18 13:36
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final Map<String, Person> repository = new HashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Person findPersonFromCache(String id) {
        return repository.get(id);
    }

    @Override
    public boolean savePersonFromCache(Person person) {
        return repository.put(person.getId(), person) == null;
    }

    @Override
    public Person findPersonFromRedis(String id) {
        return (Person) redisTemplate.opsForValue().get(id);
    }

    @Override
    public boolean savePersonFromRedis(Person person) {
        redisTemplate.opsForValue().set(person.getId(), person);
        return true;
    }
}
