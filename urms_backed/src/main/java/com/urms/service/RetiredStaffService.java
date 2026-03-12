package com.urms.service;

import com.urms.entity.RetiredStaff;
import java.util.List;
import java.util.Map;

/**
 * 退休人员Service接口
 */
public interface RetiredStaffService {

    /**
     * 根据ID查询
     */
    RetiredStaff findById(Integer staffId);

    /**
     * 查询所有
     */
    List<RetiredStaff> findAll();

    /**
     * 条件搜索
     */
    List<RetiredStaff> search(Map<String, Object> params);

    /**
     * 新增或更新（登记/修改个人信息）
     */
    int save(RetiredStaff staff);

    /**
     * 删除
     */
    int delete(Integer staffId);
}