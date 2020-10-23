package com.tamecode.springjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户
 *
 * @author Liqc
 * @date 2020/10/16 15:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdmin {

    private Long id;
    private String username;
    private String password;
    private Integer status;

    private Date createTime;

}
