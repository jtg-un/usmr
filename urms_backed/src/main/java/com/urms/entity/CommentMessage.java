package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评论留言实体类
 */
public class CommentMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer cmId;
    private Integer userId;
    private Integer targetId;   // 目标ID（公告ID或活动ID）
    private Integer targetType;  // 1: 公告评论, 2: 活动评论
    private String content;
    private LocalDateTime createTime;

    // 用户名（非数据库字段）
    private String userName;

    public CommentMessage() {}

    public Integer getCmId() {
        return cmId;
    }

    public void setCmId(Integer cmId) {
        this.cmId = cmId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}