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
// 使用 @Import 导入 {@link WebConfiguration} 纳入spring 容器中，并且是全类名，
// 如: com.tamecode.config.WebConfiguration。(@Bean导入的是 webConfiguration)
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
}
