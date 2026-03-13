package com.urms.controller;

import com.urms.entity.Activity;
import com.urms.entity.ActivityRegistration;
import com.urms.entity.CommentMessage;
import com.urms.service.ActivityService;
import com.urms.service.CommentMessageService;
import com.urms.service.RetiredStaffService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 活动控制器
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CommentMessageService commentMessageService;

    @Autowired
    private RetiredStaffService retiredStaffService;

    /**
     * 获取活动列表
     */
    @GetMapping("/list")
    public Result getList() {
        List<Activity> list = activityService.findAll();
        return Result.success(list);
    }

    /**
     * 获取活动详情
     */
    @GetMapping("/{activityId}")
    public Result getDetail(HttpServletRequest request, @PathVariable Integer activityId) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            return Result.error("活动不存在");
        }

        // 检查用户是否已报名和已投票
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role == 1 && userId != null) {
            activity.setHasRegistered(activityService.checkRegistered(activityId, userId));
            activity.setHasVoted(activityService.checkVoted(activityId, userId));
        }

        return Result.success(activity);
    }

    /**
     * 检查投票权限
     */
    @GetMapping("/vote-permission")
    public Result checkVotePermission(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以投票");
        }

        java.util.Map<String, Object> result = activityService.checkVotePermission(userId);
        return Result.success(result);
    }

    /**
     * 投票
     */
    @PostMapping("/{activityId}/vote")
    public Result vote(HttpServletRequest request, @PathVariable Integer activityId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以投票");
        }

        java.util.Map<String, Object> result = activityService.vote(activityId, userId);
        if ((Boolean) result.get("success")) {
            return Result.success((String) result.get("message"));
        } else {
            return Result.error((String) result.get("message"));
        }
    }

    /**
     * 获取活动的已有组名及人数列表
     */
    @GetMapping("/{activityId}/group-names")
    public Result getGroupNames(HttpServletRequest request, @PathVariable Integer activityId) {
        List<java.util.Map<String, Object>> groups = activityService.getGroupsWithCount(activityId);
        return Result.success(groups);
    }

    /**
     * 报名
     */
    @PostMapping("/{activityId}/register")
    public Result register(HttpServletRequest request, @PathVariable Integer activityId, @RequestBody java.util.Map<String, String> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以报名");
        }

        // 检查是否已报名
        if (activityService.checkRegistered(activityId, userId)) {
            return Result.error("您已报名此活动");
        }

        String groupName = params.get("groupName");
        int result = activityService.register(activityId, userId, groupName);
        if (result > 0) {
            return Result.success("报名成功");
        }
        return Result.error("报名失败");
    }

    /**
     * 取消报名
     */
    @DeleteMapping("/register/{regId}")
    public Result cancelRegister(HttpServletRequest request, @PathVariable Integer regId) {
        Integer userId = (Integer) request.getAttribute("userId");
        int result = activityService.cancelRegistration(regId, userId);
        if (result > 0) {
            return Result.success("取消报名成功");
        }
        return Result.error("取消报名失败");
    }

    /**
     * 我的报名记录
     */
    @GetMapping("/my-registrations")
    public Result getMyRegistrations(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以查看");
        }

        List<ActivityRegistration> list = activityService.getRegistrations(null);
        // 过滤出当前用户的报名
        List<ActivityRegistration> myRegistrations = new java.util.ArrayList<>();
        for (ActivityRegistration reg : list) {
            if (reg.getStaffId().equals(userId)) {
                myRegistrations.add(reg);
            }
        }
        return Result.success(myRegistrations);
    }

    /**
     * 获取活动评论
     */
    @GetMapping("/{activityId}/comments")
    public Result getComments(@PathVariable Integer activityId) {
        List<CommentMessage> comments = commentMessageService.findByTarget(activityId, 2);
        return Result.success(comments);
    }

    /**
     * 发表评论
     */
    @PostMapping("/{activityId}/comment")
    public Result addComment(HttpServletRequest request, @PathVariable Integer activityId, @RequestBody CommentMessage comment) {
        Integer userId = (Integer) request.getAttribute("userId");

        comment.setUserId(userId);
        comment.setTargetId(activityId);
        comment.setTargetType(2); // 活动评论

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
     * 新建活动（管理员）
     */
    @PostMapping("/create")
    public Result create(HttpServletRequest request, @RequestBody java.util.Map<String, Object> params) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        Activity activity = new Activity();
        activity.setTitle((String) params.get("title"));
        activity.setDescription((String) params.get("description"));
        activity.setType(params.get("type") != null ? (Integer) params.get("type") : 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (params.get("startTime") != null && !params.get("startTime").toString().isEmpty()) {
            activity.setStartTime(LocalDateTime.parse(params.get("startTime").toString(), formatter));
        }
        if (params.get("endTime") != null && !params.get("endTime").toString().isEmpty()) {
            activity.setEndTime(LocalDateTime.parse(params.get("endTime").toString(), formatter));
        }

        int result = activityService.add(activity);
        if (result > 0) {
            return Result.success("创建成功");
        }
        return Result.error("创建失败");
    }

    /**
     * 删除活动（管理员）
     */
    @DeleteMapping("/{activityId}")
    public Result delete(HttpServletRequest request, @PathVariable Integer activityId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        int result = activityService.delete(activityId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 获取报名统计（管理员）
     */
    @GetMapping("/{activityId}/registrations")
    public Result getRegistrations(HttpServletRequest request, @PathVariable Integer activityId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<ActivityRegistration> list = activityService.getRegistrations(activityId);
        return Result.success(list);
    }
}