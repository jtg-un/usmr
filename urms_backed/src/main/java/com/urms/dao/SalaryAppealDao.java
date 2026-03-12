package com.urms.dao;

import com.urms.entity.SalaryAppeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 工资申诉DAO接口
 */
@Mapper
public interface SalaryAppealDao {

    /**
     * 根据员工ID查询申诉
     */
    List<SalaryAppeal> findByStaffId(Integer staffId);

    /**
     * 根据工资记录ID查询申诉
     */
    SalaryAppeal findBySalaryId(@Param("salaryId") Integer salaryId, @Param("staffId") Integer staffId);

    /**
     * 查询所有申诉
     */
    List<SalaryAppeal> findAll();

    /**
     * 根据ID查询申诉
     */
    SalaryAppeal findById(Integer appealId);

    /**
     * 新增申诉
     */
    int insert(SalaryAppeal appeal);

    /**
     * 更新申诉
     */
    int update(SalaryAppeal appeal);

    /**
     * 删除申诉
     */
    int delete(Integer appealId);
}