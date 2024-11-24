package com.yuke.springboot.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_info")
public class SysUserInfo {
    private long id;
    private String username;
    private String loginname;
    private String password;
    private String remark;
}
