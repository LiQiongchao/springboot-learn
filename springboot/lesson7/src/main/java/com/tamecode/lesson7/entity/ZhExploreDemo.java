package com.tamecode.lesson7.entity;

import lombok.Data;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/15 19:49
 */
@Data
public class ZhExploreDemo {

    private Long id;

    private String exploreName;

    private String url;

//    private String digest;
    private ExploreDigest digest;

}
