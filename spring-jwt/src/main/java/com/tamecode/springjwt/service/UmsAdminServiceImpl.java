package com.tamecode.springjwt.service;

import com.tamecode.springjwt.model.UmsAdmin;
import com.tamecode.springjwt.model.UmsPermission;
import com.tamecode.springjwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Liqc
 * @date 2020/10/16 15:09
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static volatile LongAdder gerId = new LongAdder();
    private static volatile ConcurrentHashMap<String, UmsAdmin> userMap = new ConcurrentHashMap<>();
    private static volatile ConcurrentHashMap<Long, List<UmsPermission>> permissionMap = new ConcurrentHashMap<>();

    static {
        // 123456 使用 org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.encode 加密
        // BCryptPasswordEncoder 每次生成的结果是不一样的，因为使用了hash，但是结果中是可以提取出来salt值的。验证的时候是使用旧的
        // salt 对用户密码进行加密再比较。
        userMap.put("admin", new UmsAdmin(getUmsAdminId(), "admin", new BCryptPasswordEncoder().encode("123456"), 1, new Date()));
        // 1234567
        userMap.put("test", new UmsAdmin(getUmsAdminId(), "test", new BCryptPasswordEncoder().encode("1234567"), 1, new Date()));
        permissionMap.put(0L, Arrays.asList(
                new UmsPermission("pms:brand:read")
                , new UmsPermission("pms:brand:create")
                , new UmsPermission("pms:brand:update")
                , new UmsPermission("pms:brand:delete")
        ));
        permissionMap.put(1L, Collections.singletonList(
                new UmsPermission("pms:brand:read")
        ));
    }

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return userMap.get(username);
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setId(getUmsAdminId());
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        if (userMap.get(umsAdmin.getUsername()) != null) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        userMap.put(umsAdmin.getUsername(), umsAdmin);
        return umsAdmin;
    }

    private static Long getUmsAdminId() {
        long id = gerId.longValue();
        gerId.increment();
        return id;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return permissionMap.get(adminId);
    }

}
