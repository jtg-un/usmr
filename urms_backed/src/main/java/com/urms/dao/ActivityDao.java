package com.urms.dao;

import com.urms.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 活动DAO接口
 */
@Mapper
public interface ActivityDao {

    /**
     * 查询所有活动
     */
    List<Activity> findAll();

    /**
     * 根据ID查询活动
     */
    Activity findById(Integer activityId);

    /**
     * 新增活动
     */
    int insert(Activity activity);

    /**
     * 更新投票数
     */
    int updateVoteCount(Integer activityId);

    /**
     * 删除活动
     */
    int delete(Integer activityId);

    /**
     * 获取活动的所有已有组名
     */
    List<String> findGroupNames(Integer activityId);

    /**
     * 获取活动的已有组名及人数
     */
    List<Map<String, Object>> findGroupsWithCount(Integer activityId);
}