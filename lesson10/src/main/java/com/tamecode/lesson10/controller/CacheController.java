package com.tamecode.lesson10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试springCache的使用
 *
 * @author Liqc
 * @date 2020/9/18 13:18
 */
@RestController
public class CacheController {

    @Autowired
    // 属性名不一致时，可以使用Qualifier注入指定名的bean
//    @Qualifier(value = "simpleCacheManager")
    private CacheManager simpleCacheManager;

    @PostMapping(value = "/cache", produces = {"application/json;"})
    public Map<String, Object> save(@RequestParam String key, @RequestParam String value) {
        // 要与定义的cache的名字应对
        Cache cache = simpleCacheManager.getCache("cache-01");
        cache.put(key, value);
        Map<String, Object> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

    @GetMapping("/cache")
    public Object get(@RequestParam String key) {
        // 要与定义的cache的名字应对
        Cache cache = simpleCacheManager.getCache("cache-01");
        Cache.ValueWrapper wrapper = cache.get(key);
        return wrapper.get();
    }

}
