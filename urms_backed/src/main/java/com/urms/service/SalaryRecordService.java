package com.urms.service;

import com.urms.entity.SalaryRecord;
import java.util.List;

/**
 * 工资记录Service接口
 */
public interface SalaryRecordService {

    /**
     * 根据员工ID查询工资记录
     */
    List<SalaryRecord> findByStaffId(Integer staffId);

    /**
     * 查询所有工资记录
     */
    List<SalaryRecord> findAll();

    /**
     * 根据ID查询工资记录
     */
    SalaryRecord findById(Integer salaryId);

    /**
     * 新增工资记录
     */
    int add(SalaryRecord record);

    /**
     * 更新工资记录
     */
    int update(SalaryRecord record);

    /**
     * 删除工资记录
     */
    int delete(Integer salaryId);
}