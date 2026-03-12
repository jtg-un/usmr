package com.urms.dao;

import com.urms.entity.StaffRelative;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 亲属信息DAO接口
 */
@Mapper
public interface StaffRelativeDao {

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
    int insert(StaffRelative relative);

    /**
     * 更新亲属
     */
    int update(StaffRelative relative);

    /**
     * 删除亲属
     */
    int delete(Integer relativeId);
}