package com.yuke.springboot.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuke.springboot.system.entity.SysUserInfo;
import com.yuke.springboot.system.mapper.SysUserMapper;
import com.yuke.springboot.system.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserInfo> implements SysUserService {

    @Override
    public SysUserInfo findSysUserInfoByLoginname(String loginname) {
        if (StringUtils.isEmpty(loginname)) {
            return null;
        }
        return baseMapper.findSysUserInfoByLoginname(loginname);
    }
}
