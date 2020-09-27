package com.tamecode.lesson9.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Liqc
 * @date 2020/9/14 15:17
 */
@Data
@Document(indexName = "person")
public class Person {

    private Long id;

    private String name;

    private Integer age;

}
