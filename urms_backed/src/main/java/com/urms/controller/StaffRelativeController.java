package com.urms.controller;

import com.urms.entity.StaffRelative;
import com.urms.service.StaffRelativeService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 亲属信息控制器
 */
@RestController
@RequestMapping("/api/relative")
public class StaffRelativeController {

    @Autowired
    private StaffRelativeService staffRelativeService;

    /**
     * 获取我的亲属信息
     */
    @GetMapping("/my-relatives")
    public Result getMyRelatives(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以查看");
        }

        List<StaffRelative> list = staffRelativeService.findByStaffId(userId);
        return Result.success(list);
    }

    /**
     * 新增亲属信息
     */
    @PostMapping("/add")
    public Result addRelative(HttpServletRequest request, @RequestBody StaffRelative relative) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以添加");
        }

        relative.setStaffId(userId);
        int result = staffRelativeService.add(relative);
        if (result > 0) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    /**
     * 更新亲属信息
     */
    @PostMapping("/update")
    public Result updateRelative(HttpServletRequest request, @RequestBody StaffRelative relative) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");

        // 验证是否是本人的亲属
        StaffRelative existing = staffRelativeService.findById(relative.getRelativeId());
        if (existing == null) {
            return Result.error("亲属信息不存在");
        }

        if (role == 1 && !existing.getStaffId().equals(userId)) {
            return Result.error("无权修改");
        }

        int result = staffRelativeService.update(relative);
        if (result > 0) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 删除亲属信息
     */
    @DeleteMapping("/{relativeId}")
    public Result deleteRelative(HttpServletRequest request, @PathVariable Integer relativeId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");

        StaffRelative existing = staffRelativeService.findById(relativeId);
        if (existing == null) {
            return Result.error("亲属信息不存在");
        }

        if (role == 1 && !existing.getStaffId().equals(userId)) {
            return Result.error("无权删除");
        }

        int result = staffRelativeService.delete(relativeId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    // ========== 管理员功能 ==========

    /**
     * 获取所有亲属信息（管理员）
     */
    @GetMapping("/list")
    public Result getAllRelatives(HttpServletRequest request) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<StaffRelative> list = staffRelativeService.findAll();
        return Result.success(list);
    }

    /**
     * 根据员工ID获取亲属信息（管理员）
     */
    @GetMapping("/staff/{staffId}")
    public Result getRelativesByStaffId(HttpServletRequest request, @PathVariable Integer staffId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<StaffRelative> list = staffRelativeService.findByStaffId(staffId);
        return Result.success(list);
    }
}