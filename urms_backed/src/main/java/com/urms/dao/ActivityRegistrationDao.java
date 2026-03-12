package com.urms.dao;

import com.urms.entity.ActivityRegistration;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 活动报名DAO接口
 */
@Mapper
public interface ActivityRegistrationDao {

    /**
     * 根据活动ID查询报名
     */
    List<ActivityRegistration> findByActivityId(Integer activityId);

    /**
     * 根据员工ID查询报名
     */
    List<ActivityRegistration> findByStaffId(Integer staffId);

    /**
     * 检查是否已报名
     */
    int checkRegistered(Map<String, Integer> params);

    /**
     * 新增报名
     */
    int insert(ActivityRegistration registration);

    /**
     * 删除报名
     */
    int delete(Integer regId);

    /**
     * 根据活动ID删除所有报名
     */
    int deleteByActivityId(Integer activityId);
}