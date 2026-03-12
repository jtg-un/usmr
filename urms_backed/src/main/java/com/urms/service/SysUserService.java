package com.urms.service;

import com.urms.entity.SysUser;
import com.urms.util.Result;

/**
 * 系统用户Service接口
 */
public interface SysUserService {

    /**
     * 用户登录
     */
    Result login(String username, String password);

    /**
     * 根据ID查询用户
     */
    SysUser findById(Integer userId);

    /**
     * 修改密码
     */
    Result updatePassword(Integer userId, String oldPassword, String newPassword);
}