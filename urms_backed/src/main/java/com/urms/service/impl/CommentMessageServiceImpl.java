package com.urms.service.impl;

import com.urms.dao.CommentMessageDao;
import com.urms.entity.CommentMessage;
import com.urms.service.CommentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论留言Service实现类
 */
@Service
public class CommentMessageServiceImpl implements CommentMessageService {

    @Autowired
    private CommentMessageDao commentMessageDao;

    @Override
    public List<CommentMessage> findByTarget(Integer targetId, Integer targetType) {
        Map<String, Integer> params = new HashMap<>();
        params.put("targetId", targetId);
        params.put("targetType", targetType);
        return commentMessageDao.findByTargetId(params);
    }

    @Override
    public CommentMessage findById(Integer cmId) {
        return commentMessageDao.findById(cmId);
    }

    @Override
    public int add(CommentMessage comment) {
        return commentMessageDao.insert(comment);
    }

    @Override
    public int delete(Integer cmId, Integer userId) {
        // 验证是否为自己的评论
        CommentMessage comment = commentMessageDao.findById(cmId);
        if (comment != null && comment.getUserId().equals(userId)) {
            return commentMessageDao.delete(cmId);
        }
        return 0;
    }
}