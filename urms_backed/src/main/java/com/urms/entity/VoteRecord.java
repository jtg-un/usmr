package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 投票记录实体类
 */
public class VoteRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer voteId;
    private Integer activityId;
    private Integer staffId;
    private LocalDateTime voteTime;

    public VoteRecord() {}

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
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

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }
}