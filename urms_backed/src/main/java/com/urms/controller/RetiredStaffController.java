package com.urms.controller;

import com.urms.entity.RetiredStaff;
import com.urms.entity.SysUser;
import com.urms.service.RetiredStaffService;
import com.urms.service.SysUserService;
import com.urms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @Autowired
    private SysUserService sysUserService;

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
            String photoUrl = "/upload/" + fileName;
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
                              @RequestParam(required = false) String jobTitle,
                              @RequestParam(required = false) Integer ageMin,
                              @RequestParam(required = false) Integer ageMax,
                              @RequestParam(required = false) String retireDateStart,
                              @RequestParam(required = false) String retireDateEnd,
                              @RequestParam(required = false) String childName,
                              @RequestParam(required = false) String idCard,
                              @RequestParam(required = false) String phone) {
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
        if (ageMin != null) {
            params.put("ageMin", ageMin);
        }
        if (ageMax != null) {
            params.put("ageMax", ageMax);
        }
        if (retireDateStart != null && !retireDateStart.isEmpty()) {
            params.put("retireDateStart", retireDateStart);
        }
        if (retireDateEnd != null && !retireDateEnd.isEmpty()) {
            params.put("retireDateEnd", retireDateEnd);
        }
        if (childName != null && !childName.isEmpty()) {
            params.put("childName", childName);
        }
        if (idCard != null && !idCard.isEmpty()) {
            params.put("idCard", idCard);
        }
        if (phone != null && !phone.isEmpty()) {
            params.put("phone", phone);
        }

        List<RetiredStaff> list = retiredStaffService.search(params);
        return Result.success(list);
    }

    /**
     * 快速搜索退休人员（用于工资记录选择员工）
     */
    @GetMapping("/staff/quickSearch")
    public Result quickSearchStaff(HttpServletRequest request, @RequestParam(required = false) String keyword) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        Map<String, Object> params = new HashMap<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 按姓名、身份证号、手机号搜索
            params.put("realName", keyword.trim());
            params.put("idCard", keyword.trim());
            params.put("phone", keyword.trim());
        }

        List<RetiredStaff> list = retiredStaffService.quickSearch(params);
        return Result.success(list);
    }

    /**
     * 新增退休人员（管理员）
     */
    @PostMapping("/staff/add")
    public Result addStaff(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String realName = (String) params.get("realName");

        // 验证必填项
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return Result.error("密码不能为空");
        }
        if (realName == null || realName.trim().isEmpty()) {
            return Result.error("姓名不能为空");
        }

        // 检查用户名是否已存在
        if (sysUserService.checkUsernameExists(username.trim())) {
            return Result.error("用户名已存在");
        }

        // 创建用户账号
        SysUser user = new SysUser();
        user.setUsername(username.trim());
        user.setPassword(password.trim());
        user.setRole(1); // 退休人员角色
        sysUserService.insert(user);

        // 创建退休人员信息
        RetiredStaff staff = new RetiredStaff();
        staff.setStaffId(user.getUserId());
        staff.setRealName(realName.trim());
        staff.setGender((String) params.get("gender"));
        staff.setIdCard((String) params.get("idCard"));
        if (params.get("age") != null && !params.get("age").toString().isEmpty()) {
            staff.setAge(Integer.parseInt(params.get("age").toString()));
        }
        staff.setNation((String) params.get("nation"));
        staff.setEducation((String) params.get("education"));
        staff.setNativePlace((String) params.get("nativePlace"));
        // 处理日期
        String workStartDateStr = (String) params.get("workStartDate");
        if (workStartDateStr != null && !workStartDateStr.isEmpty()) {
            staff.setWorkStartDateStr(workStartDateStr);
        }
        String retireDateStr = (String) params.get("retireDate");
        if (retireDateStr != null && !retireDateStr.isEmpty()) {
            staff.setRetireDateStr(retireDateStr);
        }
        staff.setFormerDept((String) params.get("formerDept"));
        staff.setJobTitle((String) params.get("jobTitle"));
        staff.setPhoto((String) params.get("photo"));

        int result = retiredStaffService.save(staff);
        if (result > 0) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    /**
     * 编辑退休人员（管理员）
     */
    @PostMapping("/staff/update")
    public Result updateStaff(HttpServletRequest request, @RequestBody RetiredStaff staff) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        if (staff.getStaffId() == null) {
            return Result.error("人员ID不能为空");
        }

        int result = retiredStaffService.save(staff);
        if (result > 0) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 删除退休人员（管理员）
     */
    @DeleteMapping("/staff/{staffId}")
    public Result deleteStaff(HttpServletRequest request, @PathVariable Integer staffId) {
        Integer role = (Integer) request.getAttribute("role");
        if (role != 2) {
            return Result.error("无权限");
        }

        // 删除退休人员信息
        retiredStaffService.delete(staffId);
        // 删除用户账号
        sysUserService.delete(staffId);

        return Result.success("删除成功");
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