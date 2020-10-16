package com.tamecode.springjwt.service;

import com.tamecode.springjwt.model.UmsAdmin;
import com.tamecode.springjwt.model.UmsPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Liqc
 * @date 2020/10/16 15:09
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static volatile ConcurrentHashMap<String, UmsAdmin> userMap = new ConcurrentHashMap<>();
    private static volatile ConcurrentHashMap<Long, List<UmsPermission>> permissionMap = new ConcurrentHashMap<>();

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return userMap.get(username);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long id) {
        return permissionMap.get(id);
    }

}
