package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户实体类
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String username;
    private String password;
    private Integer role;  // 1: 退休人员, 2: 管理员
    private LocalDateTime createTime;

    // 关联的退休人员信息（仅退休人员角色有）
    private RetiredStaff staffInfo;

    public SysUser() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public RetiredStaff getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(RetiredStaff staffInfo) {
        this.staffInfo = staffInfo;
    }
}