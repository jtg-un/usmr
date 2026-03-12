package com.urms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 校园公告实体类
 */
public class CampusNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer noticeId;
    private String title;
    private String content;
    private LocalDateTime publishTime;
    private Integer adminId;

    // 管理员名称（非数据库字段）
    private String adminName;

    public CampusNotice() {}

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}