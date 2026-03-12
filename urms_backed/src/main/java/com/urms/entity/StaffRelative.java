package com.urms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 亲属信息实体类
 */
public class StaffRelative implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer relativeId;
    private Integer staffId;
    private String relationType;  // 配偶、子女
    private String name;
    private String address;
    private BigDecimal livingArea;
    private String phone;
    private String email;
    private String workplace;
    private String qqWechat;

    // 员工姓名（非数据库字段）
    private String staffName;

    public StaffRelative() {}

    public Integer getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Integer relativeId) {
        this.relativeId = relativeId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(BigDecimal livingArea) {
        this.livingArea = livingArea;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getQqWechat() {
        return qqWechat;
    }

    public void setQqWechat(String qqWechat) {
        this.qqWechat = qqWechat;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}