package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动实体类
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer activityId;
    private String title;
    private String description;
    private Integer type;  // 1: 普通活动, 2: 多人活动(大合唱等)
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer voteCount;
    private Integer voteLimit;  // 投票次数限制，默认10次

    // 用户是否已投票（非数据库字段）
    private Boolean hasVoted;
    // 用户是否已报名（非数据库字段）
    private Boolean hasRegistered;
    // 用户今日已投票次数（非数据库字段）
    private Integer todayVoteCount;

    public Activity() {}

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public Boolean getHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public Integer getVoteLimit() {
        return voteLimit;
    }

    public void setVoteLimit(Integer voteLimit) {
        this.voteLimit = voteLimit;
    }

    public Integer getTodayVoteCount() {
        return todayVoteCount;
    }

    public void setTodayVoteCount(Integer todayVoteCount) {
        this.todayVoteCount = todayVoteCount;
    }
}