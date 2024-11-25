package com.yuke.springboot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuke.springboot.system.entity.SysUserInfo;

public interface SysUserService extends IService<SysUserInfo> {
    public SysUserInfo findSysUserInfoByLoginname(String loginname);
}
