package com.tamecode.lesson6.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/11 22:22
 */
@Data
public class Explore {

    private Long id;
    private String name;
    private String url;
    private String page;
    private String digest;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime idate;


}
