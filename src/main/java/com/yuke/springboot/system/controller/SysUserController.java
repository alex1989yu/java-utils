package com.yuke.springboot.system.controller;

import com.yuke.springboot.system.entity.SysUserInfo;
import com.yuke.springboot.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/list")
    public void list() {
        List<SysUserInfo> list = sysUserService.list();
        System.out.println(list);
    }
}
