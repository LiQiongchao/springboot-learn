package com.tamecode.springjwt.service;

import com.tamecode.springjwt.model.UmsAdmin;
import com.tamecode.springjwt.model.UmsPermission;

import java.util.List;

/**
 * @author Liqc
 * @date 2020/10/16 15:08
 */
public interface UmsAdminService {
    /**
     * 获取用户名
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户权限列表
     * @param id
     * @return
     */
    List<UmsPermission> getPermissionList(Long id);
}
