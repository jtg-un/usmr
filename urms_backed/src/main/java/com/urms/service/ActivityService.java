package com.urms.service;

import com.urms.entity.Activity;
import com.urms.entity.ActivityRegistration;
import java.util.List;
import java.util.Map;

/**
 * 活动Service接口
 */
public interface ActivityService {

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
    int add(Activity activity);

    /**
     * 删除活动
     */
    int delete(Integer activityId);

    /**
     * 投票（带限制）
     */
    Map<String, Object> vote(Integer activityId, Integer staffId);

    /**
     * 检查投票权限
     */
    Map<String, Object> checkVotePermission(Integer staffId);

    /**
     * 报名
     */
    int register(Integer activityId, Integer staffId, String groupName);

    /**
     * 获取活动的已有组名列表
     */
    List<String> getGroupNames(Integer activityId);

    /**
     * 获取活动的已有组名及人数
     */
    List<Map<String, Object>> getGroupsWithCount(Integer activityId);

    /**
     * 查询活动报名列表
     */
    List<ActivityRegistration> getRegistrations(Integer activityId);

    /**
     * 检查是否已报名
     */
    boolean checkRegistered(Integer activityId, Integer staffId);

    /**
     * 取消报名
     */
    int cancelRegistration(Integer regId, Integer staffId);

    /**
     * 检查用户是否已对活动投票
     */
    boolean checkVoted(Integer activityId, Integer staffId);
}