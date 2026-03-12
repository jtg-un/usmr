package com.urms.controller;

import com.urms.entity.SalaryAppeal;
import com.urms.entity.SalaryRecord;
import com.urms.service.SalaryAppealService;
import com.urms.service.SalaryRecordService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工资控制器
 */
@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryRecordService salaryRecordService;

    @Autowired
    private SalaryAppealService salaryAppealService;

    /**
     * 获取我的工资记录
     */
    @GetMapping("/my-records")
    public Result getMyRecords(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以查看");
        }

        List<SalaryRecord> list = salaryRecordService.findByStaffId(userId);
        return Result.success(list);
    }

    /**
     * 获取工资详情
     */
    @GetMapping("/{salaryId}")
    public Result getDetail(HttpServletRequest request, @PathVariable Integer salaryId) {
        SalaryRecord record = salaryRecordService.findById(salaryId);
        if (record == null) {
            return Result.error("工资记录不存在");
        }
        return Result.success(record);
    }

    /**
     * 提交申诉
     */
    @PostMapping("/appeal")
    public Result submitAppeal(HttpServletRequest request, @RequestBody SalaryAppeal appeal) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以提交申诉");
        }

        // 检查是否已申诉
        SalaryAppeal existing = salaryAppealService.findBySalaryId(appeal.getSalaryId(), userId);
        if (existing != null) {
            return Result.error("该工资记录已提交过申诉，不能重复申诉");
        }

        appeal.setStaffId(userId);
        appeal.setStatus(0);

        int result = salaryAppealService.submit(appeal);
        if (result > 0) {
            return Result.success("申诉已提交");
        }
        return Result.error("提交失败");
    }

    /**
     * 取消申诉
     */
    @DeleteMapping("/appeal/{appealId}")
    public Result cancelAppeal(HttpServletRequest request, @PathVariable Integer appealId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以取消申诉");
        }

        int result = salaryAppealService.cancel(appealId, userId);
        if (result > 0) {
            return Result.success("申诉已取消");
        }
        return Result.error("取消失败，申诉不存在或已处理");
    }

    /**
     * 检查是否已申诉
     */
    @GetMapping("/appeal/check/{salaryId}")
    public Result checkAppeal(HttpServletRequest request, @PathVariable Integer salaryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("无权限");
        }

        SalaryAppeal appeal = salaryAppealService.findBySalaryId(salaryId, userId);
        return Result.success(appeal);
    }

    /**
     * 获取我的申诉记录
     */
    @GetMapping("/my-appeals")
    public Result getMyAppeals(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");
        if (role != 1) {
            return Result.error("只有退休人员可以查看");
        }

        List<SalaryAppeal> list = salaryAppealService.findByStaffId(userId);
        return Result.success(list);
    }

    // ========== 管理员功能 ==========

    /**
     * 获取所有工资记录（管理员）
     */
    @GetMapping("/list")
    public Result getAllRecords(HttpServletRequest request) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<SalaryRecord> list = salaryRecordService.findAll();
        return Result.success(list);
    }

    /**
     * 新增工资记录（管理员）
     */
    @PostMapping("/add")
    public Result addRecord(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        try {
            SalaryRecord record = new SalaryRecord();
            record.setStaffId(Integer.parseInt(params.get("staffId").toString()));
            record.setYearMonth((String) params.get("yearMonth"));

            // 处理可能为空的数值字段
            String baseSalaryStr = params.get("baseSalary") != null ? params.get("baseSalary").toString() : "0";
            String subsidyStr = params.get("subsidy") != null ? params.get("subsidy").toString() : "0";
            String condolenceFeeStr = params.get("condolenceFee") != null ? params.get("condolenceFee").toString() : "0";

            record.setBaseSalary(new BigDecimal(baseSalaryStr.isEmpty() ? "0" : baseSalaryStr));
            record.setSubsidy(new BigDecimal(subsidyStr.isEmpty() ? "0" : subsidyStr));
            record.setCondolenceFee(new BigDecimal(condolenceFeeStr.isEmpty() ? "0" : condolenceFeeStr));
            record.setChangeReason(params.get("changeReason") != null ? (String) params.get("changeReason") : "");
            record.setStatus(0);

            int result = salaryRecordService.add(record);
            if (result > 0) {
                return Result.success("添加成功");
            }
            return Result.error("添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败: " + e.getMessage());
        }
    }

    /**
     * 更新工资记录（管理员）
     */
    @PostMapping("/update")
    public Result updateRecord(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        try {
            SalaryRecord record = new SalaryRecord();
            record.setSalaryId(Integer.parseInt(params.get("salaryId").toString()));

            if (params.get("baseSalary") != null && !params.get("baseSalary").toString().isEmpty()) {
                record.setBaseSalary(new BigDecimal(params.get("baseSalary").toString()));
            }
            if (params.get("subsidy") != null && !params.get("subsidy").toString().isEmpty()) {
                record.setSubsidy(new BigDecimal(params.get("subsidy").toString()));
            }
            if (params.get("condolenceFee") != null && !params.get("condolenceFee").toString().isEmpty()) {
                record.setCondolenceFee(new BigDecimal(params.get("condolenceFee").toString()));
            }
            record.setChangeReason(params.get("changeReason") != null ? (String) params.get("changeReason") : "");

            int result = salaryRecordService.update(record);
            if (result > 0) {
                return Result.success("更新成功");
            }
            return Result.error("更新失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败: " + e.getMessage());
        }
    }

    /**
     * 删除工资记录（管理员）
     */
    @DeleteMapping("/{salaryId}")
    public Result deleteRecord(HttpServletRequest request, @PathVariable Integer salaryId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        int result = salaryRecordService.delete(salaryId);
        if (result > 0) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 获取所有申诉（管理员）
     */
    @GetMapping("/appeals")
    public Result getAllAppeals(HttpServletRequest request) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        List<SalaryAppeal> list = salaryAppealService.findAll();
        return Result.success(list);
    }

    /**
     * 回复申诉（管理员）
     */
    @PostMapping("/appeal/{appealId}/reply")
    public Result replyAppeal(HttpServletRequest request, @PathVariable Integer appealId, @RequestBody Map<String, String> params) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        String reply = params.get("reply");
        int result = salaryAppealService.reply(appealId, reply);
        if (result > 0) {
            return Result.success("回复成功");
        }
        return Result.error("回复失败");
    }
}