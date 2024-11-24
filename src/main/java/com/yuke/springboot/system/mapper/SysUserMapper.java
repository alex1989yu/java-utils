package com.yuke.springboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuke.springboot.system.entity.SysUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserInfo> {

    @Select("select * from sys_user_info where loginname=#{loginname}")
    public SysUserInfo findSysUserInfoByLoginname(String loginname);
}
