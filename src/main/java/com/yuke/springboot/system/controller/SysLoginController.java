package com.yuke.springboot.system.controller;

import com.yuke.springboot.system.entity.SysUserInfo;
import com.yuke.springboot.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sys")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public void login(String loginname, String password) {
        SysUserInfo user = sysUserService.findSysUserInfoByLoginname(loginname);
        ValueOperations<String, SysUserInfo> ops = redisTemplate.opsForValue();
        ops.set("sys:user:info:" + user.getId(), user);
        System.out.println(user);
    }
}
