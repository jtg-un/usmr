package com.urms.service.impl;

import com.urms.dao.SysUserDao;
import com.urms.entity.SysUser;
import com.urms.service.SysUserService;
import com.urms.util.JwtUtil;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户Service实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Result login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        SysUser user = sysUserDao.findByUsername(username.trim());
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        // 生成token
        String token = JwtUtil.generateToken(user.getUserId(), user.getUsername(), user.getRole());

        // 构建返回数据
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("token", token);
        data.put("userId", user.getUserId());
        data.put("username", user.getUsername());
        data.put("role", user.getRole());
        if (user.getRole() == 1 && user.getStaffInfo() != null) {
            data.put("realName", user.getStaffInfo().getRealName());
            data.put("staffId", user.getStaffInfo().getStaffId());
        }

        return Result.success("登录成功", data);
    }

    @Override
    public SysUser findById(Integer userId) {
        return sysUserDao.findById(userId);
    }

    @Override
    public Result updatePassword(Integer userId, String oldPassword, String newPassword) {
        SysUser user = sysUserDao.findById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("原密码错误");
        }

        if (newPassword == null || newPassword.trim().isEmpty()) {
            return Result.error("新密码不能为空");
        }

        sysUserDao.updatePassword(userId, newPassword.trim());
        return Result.success("密码修改成功");
    }
}