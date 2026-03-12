package com.urms.dao;

import com.urms.entity.CommentMessage;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 评论留言DAO接口
 */
@Mapper
public interface CommentMessageDao {

    /**
     * 根据目标查询评论
     */
    List<CommentMessage> findByTargetId(Map<String, Integer> params);

    /**
     * 根据ID查询评论
     */
    CommentMessage findById(Integer cmId);

    /**
     * 新增评论
     */
    int insert(CommentMessage comment);

    /**
     * 删除评论
     */
    int delete(Integer cmId);

    /**
     * 根据目标删除所有评论
     */
    int deleteByTargetId(Map<String, Integer> params);
}