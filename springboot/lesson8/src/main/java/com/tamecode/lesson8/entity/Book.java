package com.tamecode.lesson8.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/22 15:49
 */
@Data
@Entity
@Table(name = "sb_jpa_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String isbn;

    private LocalDateTime publishDate;

    @ManyToMany(mappedBy = "books")
    private Collection<Customer> customers;


}
