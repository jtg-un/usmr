package com.urms.service;

import com.urms.entity.CampusNotice;
import java.util.List;

/**
 * 校园公告Service接口
 */
public interface CampusNoticeService {

    /**
     * 查询所有公告
     */
    List<CampusNotice> findAll();

    /**
     * 根据ID查询公告
     */
    CampusNotice findById(Integer noticeId);

    /**
     * 发布公告
     */
    int publish(CampusNotice notice);

    /**
     * 删除公告
     */
    int delete(Integer noticeId);
}