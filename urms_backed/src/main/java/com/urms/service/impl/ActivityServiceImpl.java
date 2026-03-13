package com.urms.service.impl;

import com.urms.dao.ActivityDao;
import com.urms.dao.ActivityRegistrationDao;
import com.urms.dao.VoteRecordDao;
import com.urms.entity.Activity;
import com.urms.entity.ActivityRegistration;
import com.urms.entity.VoteRecord;
import com.urms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动Service实现类
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityRegistrationDao registrationDao;

    @Autowired
    private VoteRecordDao voteRecordDao;

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

    @Override
    public Activity findById(Integer activityId) {
        return activityDao.findById(activityId);
    }

    @Override
    public int add(Activity activity) {
        activity.setVoteCount(0);
        activity.setVoteLimit(10); // 默认每日10次
        return activityDao.insert(activity);
    }

    @Override
    @Transactional
    public int delete(Integer activityId) {
        // 先删除相关报名
        registrationDao.deleteByActivityId(activityId);
        // 再删除活动
        return activityDao.delete(activityId);
    }

    @Override
    @Transactional
    public Map<String, Object> vote(Integer activityId, Integer staffId) {
        Map<String, Object> result = new HashMap<>();

        // 检查是否已对该活动投票（每人每个活动只能投1票）
        int votedCount = voteRecordDao.checkVoted(activityId, staffId);
        System.out.println("检查投票记录: activityId=" + activityId + ", staffId=" + staffId + ", votedCount=" + votedCount);

        if (votedCount > 0) {
            result.put("success", false);
            result.put("message", "您已对该活动投过票了，每人只能投1票");
            return result;
        }

        // 记录投票
        VoteRecord record = new VoteRecord();
        record.setActivityId(activityId);
        record.setStaffId(staffId);
        int insertResult = voteRecordDao.insert(record);
        System.out.println("插入投票记录结果: " + insertResult);

        // 更新投票数
        int updateResult = activityDao.updateVoteCount(activityId);
        System.out.println("更新投票数结果: " + updateResult);

        result.put("success", true);
        result.put("message", "投票成功");
        return result;
    }

    @Override
    public Map<String, Object> checkVotePermission(Integer staffId) {
        Map<String, Object> result = new HashMap<>();
        // 每人每个活动只能投1票
        result.put("limit", 1);
        result.put("canVote", true);
        return result;
    }

    @Override
    public int register(Integer activityId, Integer staffId, String groupName) {
        ActivityRegistration reg = new ActivityRegistration();
        reg.setActivityId(activityId);
        reg.setStaffId(staffId);
        reg.setGroupName(groupName);
        return registrationDao.insert(reg);
    }

    @Override
    public List<String> getGroupNames(Integer activityId) {
        return activityDao.findGroupNames(activityId);
    }

    @Override
    public List<Map<String, Object>> getGroupsWithCount(Integer activityId) {
        return activityDao.findGroupsWithCount(activityId);
    }

    @Override
    public List<ActivityRegistration> getRegistrations(Integer activityId) {
        return registrationDao.findByActivityId(activityId);
    }

    @Override
    public boolean checkRegistered(Integer activityId, Integer staffId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("activityId", activityId);
        params.put("staffId", staffId);
        return registrationDao.checkRegistered(params) > 0;
    }

    @Override
    public int cancelRegistration(Integer regId, Integer staffId) {
        return registrationDao.delete(regId);
    }

    @Override
    public boolean checkVoted(Integer activityId, Integer staffId) {
        return voteRecordDao.checkVoted(activityId, staffId) > 0;
    }
}