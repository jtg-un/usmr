package com.urms.controller;

import com.urms.entity.SysUser;
import com.urms.service.SysUserService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody java.util.Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        return sysUserService.login(username, password);
    }

    /**
     * 修改密码
     */
    @PostMapping("/password")
    public Result updatePassword(HttpServletRequest request, @RequestBody java.util.Map<String, String> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");

        if (userId == null) {
            return Result.error("未登录或登录已过期");
        }
        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            return Result.error("请输入原密码");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return Result.error("请输入新密码");
        }

        return sysUserService.updatePassword(userId, oldPassword, newPassword);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        SysUser user = sysUserService.findById(userId);
        if (user != null) {
            user.setPassword(null); // 不返回密码
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }
}