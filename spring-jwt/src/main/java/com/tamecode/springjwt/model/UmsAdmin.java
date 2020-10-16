package com.tamecode.springjwt.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author Liqc
 * @date 2020/10/16 15:00
 */
@Data
public class UmsAdmin {

    private Long id;
    private String username;
    private String password;
    private Integer status;

    private Date createTime;


}
