package com.urms.service.impl;

import com.urms.dao.SalaryAppealDao;
import com.urms.entity.SalaryAppeal;
import com.urms.service.SalaryAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 工资申诉Service实现类
 */
@Service
public class SalaryAppealServiceImpl implements SalaryAppealService {

    @Autowired
    private SalaryAppealDao salaryAppealDao;

    @Override
    public List<SalaryAppeal> findByStaffId(Integer staffId) {
        return salaryAppealDao.findByStaffId(staffId);
    }

    @Override
    public List<SalaryAppeal> findAll() {
        return salaryAppealDao.findAll();
    }

    @Override
    public SalaryAppeal findById(Integer appealId) {
        return salaryAppealDao.findById(appealId);
    }

    @Override
    public int submit(SalaryAppeal appeal) {
        appeal.setStatus(0); // 待处理
        return salaryAppealDao.insert(appeal);
    }

    @Override
    public int reply(Integer appealId, String reply) {
        SalaryAppeal appeal = new SalaryAppeal();
        appeal.setAppealId(appealId);
        appeal.setReply(reply);
        appeal.setStatus(1); // 已回复
        return salaryAppealDao.update(appeal);
    }
}