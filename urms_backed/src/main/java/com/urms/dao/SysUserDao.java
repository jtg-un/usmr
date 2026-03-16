package com.urms.dao;

import com.urms.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户DAO接口
 */
@Mapper
public interface SysUserDao {

    /**
     * 根据用户名查询用户
     */
    SysUser findByUsername(String username);

    /**
     * 根据ID查询用户
     */
    SysUser findById(Integer userId);

    /**
     * 新增用户
     */
    int insert(SysUser user);

    /**
     * 更新密码
     */
    int updatePassword(@Param("userId") Integer userId, @Param("password") String password);

    /**
     * 删除用户
     */
    int delete(Integer userId);

    /**
     * 检查用户名是否存在
     */
    int checkUsernameExists(String username);
}