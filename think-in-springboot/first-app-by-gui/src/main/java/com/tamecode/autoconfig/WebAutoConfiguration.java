package com.tamecode.autoconfig;

import com.tamecode.config.WebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Web自动装配类
 *
 * @Author: LiQiongchao
 * @Date: 2020/12/23 21:57
 */
@Configuration
// 使用 @Import 导入 {@link WebConfiguration}
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
}
