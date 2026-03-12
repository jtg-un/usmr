package com.urms.service.impl;

import com.urms.dao.ActivityDao;
import com.urms.dao.ActivityRegistrationDao;
import com.urms.entity.Activity;
import com.urms.entity.ActivityRegistration;
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
    public int vote(Integer activityId) {
        return activityDao.updateVoteCount(activityId);
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
}