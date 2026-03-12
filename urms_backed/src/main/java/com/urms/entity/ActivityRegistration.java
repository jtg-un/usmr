package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动报名实体类
 */
public class ActivityRegistration implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer regId;
    private Integer activityId;
    private Integer staffId;
    private LocalDateTime regTime;
    private String groupName;  // 多人报名时的组名

    // 关联信息（非数据库字段）
    private String activityTitle;
    private String staffName;

    public ActivityRegistration() {}

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}