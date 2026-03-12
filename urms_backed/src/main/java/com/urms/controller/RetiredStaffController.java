package com.urms.controller;

import com.urms.entity.RetiredStaff;
import com.urms.service.RetiredStaffService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 退休人员信息控制器
 */
@RestController
@RequestMapping("/api")
public class RetiredStaffController {

    @Autowired
    private RetiredStaffService retiredStaffService;

    /**
     * 获取个人信息
     */
    @GetMapping("/profile")
    public Result getProfile(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");

        if (role != 1) {
            return Result.error("只有退休人员可以查看个人信息");
        }

        RetiredStaff staff = retiredStaffService.findById(userId);
        return Result.success(staff);
    }

    /**
     * 保存个人信息
     */
    @PostMapping("/profile")
    public Result saveProfile(HttpServletRequest request, @RequestBody RetiredStaff staff) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer role = (Integer) request.getAttribute("role");

        if (role != 1) {
            return Result.error("只有退休人员可以修改个人信息");
        }

        staff.setStaffId(userId);
        int result = retiredStaffService.save(staff);
        if (result > 0) {
            return Result.success("保存成功");
        }
        return Result.error("保存失败");
    }

    /**
     * 上传照片
     */
    @PostMapping("/upload/photo")
    public Result uploadPhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return Result.error("请选择文件");
        }

        try {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String fileName = UUID.randomUUID().toString() + extension;

            // 获取webapp下的upload目录路径
            String uploadPath = request.getServletContext().getRealPath("/upload/");
            System.out.println("ServletContext realPath: " + uploadPath);

            if (uploadPath == null) {
                return Result.error("无法获取上传路径，请检查服务器配置");
            }

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                System.out.println("Create upload directory: " + created);
                if (!created) {
                    return Result.error("无法创建上传目录");
                }
            }

            System.out.println("Upload directory: " + uploadDir.getAbsolutePath());

            // 保存文件
            File destFile = new File(uploadDir, fileName);
            file.transferTo(destFile);
            System.out.println("File saved to: " + destFile.getAbsolutePath());
            System.out.println("File exists: " + destFile.exists());

            // 返回可访问的URL路径
            String photoUrl = "/urms/upload/" + fileName;
            System.out.println("Photo URL: " + photoUrl);

            return Result.success("上传成功", photoUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return ".jpg";
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex);
        }
        return ".jpg";
    }

    // ========== 管理员功能 ==========

    /**
     * 获取所有退休人员列表（管理员）
     */
    @GetMapping("/staff/list")
    public Result getAllStaff(HttpServletRequest request) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }
        List<RetiredStaff> list = retiredStaffService.findAll();
        return Result.success(list);
    }

    /**
     * 搜索退休人员（管理员）
     */
    @GetMapping("/staff/search")
    public Result searchStaff(HttpServletRequest request,
                              @RequestParam(required = false) String realName,
                              @RequestParam(required = false) String formerDept,
                              @RequestParam(required = false) String jobTitle) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        Map<String, Object> params = new HashMap<>();
        if (realName != null && !realName.isEmpty()) {
            params.put("realName", realName);
        }
        if (formerDept != null && !formerDept.isEmpty()) {
            params.put("formerDept", formerDept);
        }
        if (jobTitle != null && !jobTitle.isEmpty()) {
            params.put("jobTitle", jobTitle);
        }

        List<RetiredStaff> list = retiredStaffService.search(params);
        return Result.success(list);
    }

    /**
     * 根据ID获取退休人员信息（管理员）
     */
    @GetMapping("/staff/{staffId}")
    public Result getStaffById(HttpServletRequest request, @PathVariable Integer staffId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }
        RetiredStaff staff = retiredStaffService.findById(staffId);
        return Result.success(staff);
    }
}