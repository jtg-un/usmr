package com.urms.dao;

import com.urms.entity.RetiredStaff;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 退休人员DAO接口
 */
@Mapper
public interface RetiredStaffDao {

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
     * 新增
     */
    int insert(RetiredStaff staff);

    /**
     * 更新
     */
    int update(RetiredStaff staff);

    /**
     * 删除
     */
    int delete(Integer staffId);
}