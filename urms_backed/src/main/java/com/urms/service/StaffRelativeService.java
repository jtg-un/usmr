package com.urms.service;

import com.urms.entity.StaffRelative;
import java.util.List;

/**
 * 亲属信息Service接口
 */
public interface StaffRelativeService {

    /**
     * 根据员工ID查询亲属
     */
    List<StaffRelative> findByStaffId(Integer staffId);

    /**
     * 查询所有亲属
     */
    List<StaffRelative> findAll();

    /**
     * 根据ID查询亲属
     */
    StaffRelative findById(Integer relativeId);

    /**
     * 新增亲属
     */
    int add(StaffRelative relative);

    /**
     * 更新亲属
     */
    int update(StaffRelative relative);

    /**
     * 删除亲属
     */
    int delete(Integer relativeId);
}