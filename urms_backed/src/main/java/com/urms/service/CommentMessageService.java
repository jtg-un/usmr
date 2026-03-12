package com.urms.service;

import com.urms.entity.CommentMessage;
import java.util.List;

/**
 * 评论留言Service接口
 */
public interface CommentMessageService {

    /**
     * 根据目标查询评论
     */
    List<CommentMessage> findByTarget(Integer targetId, Integer targetType);

    /**
     * 根据ID查询评论
     */
    CommentMessage findById(Integer cmId);

    /**
     * 发表评论
     */
    int add(CommentMessage comment);

    /**
     * 删除评论（需验证是否为自己的评论）
     */
    int delete(Integer cmId, Integer userId);
}