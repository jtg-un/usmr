package com.urms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工资记录实体类
 */
public class SalaryRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer salaryId;
    private Integer staffId;
    private String yearMonth;
    private BigDecimal baseSalary;
    private BigDecimal subsidy;
    private BigDecimal condolenceFee;
    private String changeReason;
    private Integer status;  // 0: 正常, 1: 有申诉

    // 员工姓名（非数据库字段）
    private String staffName;
    // 员工工号（非数据库字段）
    private String staffUsername;

    public SalaryRecord() {}

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(BigDecimal subsidy) {
        this.subsidy = subsidy;
    }

    public BigDecimal getCondolenceFee() {
        return condolenceFee;
    }

    public void setCondolenceFee(BigDecimal condolenceFee) {
        this.condolenceFee = condolenceFee;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }
}