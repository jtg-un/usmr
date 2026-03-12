package com.urms.service.impl;

import com.urms.dao.RetiredStaffDao;
import com.urms.entity.RetiredStaff;
import com.urms.service.RetiredStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 退休人员Service实现类
 */
@Service
public class RetiredStaffServiceImpl implements RetiredStaffService {

    @Autowired
    private RetiredStaffDao retiredStaffDao;

    @Override
    public RetiredStaff findById(Integer staffId) {
        return retiredStaffDao.findById(staffId);
    }

    @Override
    public List<RetiredStaff> findAll() {
        return retiredStaffDao.findAll();
    }

    @Override
    public List<RetiredStaff> search(Map<String, Object> params) {
        return retiredStaffDao.search(params);
    }

    @Override
    public int save(RetiredStaff staff) {
        // 检查是否存在
        RetiredStaff existing = retiredStaffDao.findById(staff.getStaffId());
        if (existing == null) {
            return retiredStaffDao.insert(staff);
        } else {
            return retiredStaffDao.update(staff);
        }
    }

    @Override
    public int delete(Integer staffId) {
        return retiredStaffDao.delete(staffId);
    }
}