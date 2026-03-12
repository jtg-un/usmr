package com.urms.service;

import com.urms.entity.SalaryAppeal;
import java.util.List;

/**
 * 工资申诉Service接口
 */
public interface SalaryAppealService {

    /**
     * 根据员工ID查询申诉
     */
    List<SalaryAppeal> findByStaffId(Integer staffId);

    /**
     * 查询所有申诉
     */
    List<SalaryAppeal> findAll();

    /**
     * 根据ID查询申诉
     */
    SalaryAppeal findById(Integer appealId);

    /**
     * 提交申诉
     */
    int submit(SalaryAppeal appeal);

    /**
     * 回复申诉
     */
    int reply(Integer appealId, String reply);
}