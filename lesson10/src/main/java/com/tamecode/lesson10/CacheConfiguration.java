package com.tamecode.lesson10;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * 缓存的配置
 *
 * @author Liqc
 * @date 2020/9/18 12:35
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager simpleCacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        // 定义缓存的Map
        ConcurrentMapCache cache = new ConcurrentMapCache("cache-01");
        simpleCacheManager.setCaches(Collections.singletonList(cache));
        return simpleCacheManager;
    }

}
