package com.urms.controller;

import com.urms.entity.CampusNotice;
import com.urms.entity.CommentMessage;
import com.urms.service.CampusNoticeService;
import com.urms.service.CommentMessageService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 校园公告控制器
 */
@RestController
@RequestMapping("/api/notice")
public class CampusNoticeController {

    @Autowired
    private CampusNoticeService campusNoticeService;

    @Autowired
    private CommentMessageService commentMessageService;

    /**
     * 获取公告列表
     */
    @GetMapping("/list")
    public Result getList() {
        List<CampusNotice> list = campusNoticeService.findAll();
        return Result.success(list);
    }

    /**
     * 获取公告详情
     */
    @GetMapping("/{noticeId}")
    public Result getDetail(@PathVariable Integer noticeId) {
        CampusNotice notice = campusNoticeService.findById(noticeId);
        if (notice == null) {
            return Result.error("公告不存在");
        }
        return Result.success(notice);
    }

    /**
     * 获取公告评论
     */
    @GetMapping("/{noticeId}/comments")
    public Result getComments(@PathVariable Integer noticeId) {
        List<CommentMessage> comments = commentMessageService.findByTarget(noticeId, 1);
        return Result.success(comments);
    }

    /**
     * 发表评论
     */
    @PostMapping("/{noticeId}/comment")
    public Result addComment(HttpServletRequest request, @PathVariable Integer noticeId, @RequestBody CommentMessage comment) {
        Integer userId = (Integer) request.getAttribute("userId");

        comment.setUserId(userId);
        comment.setTargetId(noticeId);
        comment.setTargetType(1); // 公告评论

        int result = commentMessageService.add(comment);
        if (result > 0) {
            return Result.success("评论成功");
        }
        return Result.error("评论失败");
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/comment/{cmId}")
    public Result deleteComment(HttpServletRequest request, @PathVariable Integer cmId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");

        CommentMessage comment = commentMessageService.findById(cmId);
        if (comment == null) {
            return Result.error("评论不存在");
        }

        // 只有评论者本人或管理员可以删除
        if (!comment.getUserId().equals(userId) && role != 2) {
            return Result.error("无权删除此评论");
        }

        int result = commentMessageService.delete(cmId, userId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    // ========== 管理员功能 ==========

    /**
     * 发布公告（管理员）
     */
    @PostMapping("/publish")
    public Result publish(HttpServletRequest request, @RequestBody CampusNotice notice) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        Integer adminId = (Integer) request.getAttribute("userId");
        notice.setAdminId(adminId);

        int result = campusNoticeService.publish(notice);
        if (result > 0) {
            return Result.success("发布成功");
        }
        return Result.error("发布失败");
    }

    /**
     * 删除公告（管理员）
     */
    @DeleteMapping("/{noticeId}")
    public Result delete(HttpServletRequest request, @PathVariable Integer noticeId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        int result = campusNoticeService.delete(noticeId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}