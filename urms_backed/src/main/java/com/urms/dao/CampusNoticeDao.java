package com.urms.dao;

import com.urms.entity.CampusNotice;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 校园公告DAO接口
 */
@Mapper
public interface CampusNoticeDao {

    /**
     * 查询所有公告
     */
    List<CampusNotice> findAll();

    /**
     * 根据ID查询公告
     */
    CampusNotice findById(Integer noticeId);

    /**
     * 新增公告
     */
    int insert(CampusNotice notice);

    /**
     * 删除公告
     */
    int delete(Integer noticeId);
}