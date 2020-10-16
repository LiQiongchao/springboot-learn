package com.tamecode.springjwt.controller;

import com.tamecode.springjwt.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Liqc
 * @date 2020/10/16 16:41
 */
@Slf4j
@RestController
public class PmsBrandController {

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @GetMapping(value = "/brand")
    public String getBrandList() {
        return "查询";
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @PutMapping(value = "/brand")
    public String updateBrand() {
        return "更新";
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @PostMapping(value = "/brand")
    public String createBrand() {
        return "创建";
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @DeleteMapping(value = "/brand")
    public String deleteBrand() {
        return "删除";
    }

}
