package com.urms.controller;

import com.urms.entity.CommentMessage;
import com.urms.service.CommentMessageService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站内留言控制器
 */
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private CommentMessageService commentMessageService;

    // 留言类型常量
    private static final int MESSAGE_TYPE = 3;

    /**
     * 提交留言
     */
    @PostMapping("/submit")
    public Result submitMessage(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        String content = params.get("content");

        if (content == null || content.trim().isEmpty()) {
            return Result.error("留言内容不能为空");
        }

        CommentMessage message = new CommentMessage();
        message.setUserId(userId);
        message.setTargetId(0); // 发给管理员
        message.setTargetType(MESSAGE_TYPE);
        message.setContent(content.trim());

        int result = commentMessageService.add(message);
        if (result > 0) {
            return Result.success("留言提交成功");
        }
        return Result.error("提交失败");
    }

    /**
     * 获取我的留言
     */
    @GetMapping("/my-messages")
    public Result getMyMessages(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");

        // 查询用户的所有留言
        Map<String, Integer> params = new HashMap<>();
        params.put("targetId", 0);
        params.put("targetType", MESSAGE_TYPE);

        List<CommentMessage> allMessages = commentMessageService.findByTarget(0, MESSAGE_TYPE);

        // 过滤出当前用户的留言
        List<CommentMessage> myMessages = new java.util.ArrayList<>();
        for (CommentMessage msg : allMessages) {
            if (msg.getUserId().equals(userId)) {
                myMessages.add(msg);
            }
        }

        return Result.success(myMessages);
    }

    /**
     * 删除留言
     */
    @DeleteMapping("/{cmId}")
    public Result deleteMessage(HttpServletRequest request, @PathVariable Integer cmId) {
        Integer userId = (Integer) request.getAttribute("userId");

        CommentMessage message = commentMessageService.findById(cmId);
        if (message == null) {
            return Result.error("留言不存在");
        }

        if (!message.getUserId().equals(userId)) {
            return Result.error("无权删除此留言");
        }

        int result = commentMessageService.delete(cmId, userId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    // ========== 管理员功能 ==========

    /**
     * 获取所有留言（管理员）
     */
    @GetMapping("/list")
    public Result getAllMessages(HttpServletRequest request) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<CommentMessage> messages = commentMessageService.findByTarget(0, MESSAGE_TYPE);
        return Result.success(messages);
    }
}