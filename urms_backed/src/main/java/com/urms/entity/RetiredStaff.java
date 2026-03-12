package com.urms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 退休人员信息实体类
 */
public class RetiredStaff implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer staffId;
    private String realName;
    private String photo;
    private String gender;
    private String idCard;
    private Integer age;
    private String nation;
    private String education;
    private String nativePlace;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate workStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate retireDate;

    private String formerDept;
    private String jobTitle;

    public RetiredStaff() {}

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
    }

    public void setWorkStartDateStr(String workStartDate) {
        if (workStartDate != null && !workStartDate.isEmpty()) {
            try {
                this.workStartDate = LocalDate.parse(workStartDate);
            } catch (Exception e) {
                // ignore parse error
            }
        }
    }

    public LocalDate getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(LocalDate retireDate) {
        this.retireDate = retireDate;
    }

    public void setRetireDateStr(String retireDate) {
        if (retireDate != null && !retireDate.isEmpty()) {
            try {
                this.retireDate = LocalDate.parse(retireDate);
            } catch (Exception e) {
                // ignore parse error
            }
        }
    }

    public String getFormerDept() {
        return formerDept;
    }

    public void setFormerDept(String formerDept) {
        this.formerDept = formerDept;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}