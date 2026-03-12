package com.urms.service.impl;

import com.urms.dao.StaffRelativeDao;
import com.urms.entity.StaffRelative;
import com.urms.service.StaffRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 亲属信息Service实现类
 */
@Service
public class StaffRelativeServiceImpl implements StaffRelativeService {

    @Autowired
    private StaffRelativeDao staffRelativeDao;

    @Override
    public List<StaffRelative> findByStaffId(Integer staffId) {
        return staffRelativeDao.findByStaffId(staffId);
    }

    @Override
    public List<StaffRelative> findAll() {
        return staffRelativeDao.findAll();
    }

    @Override
    public StaffRelative findById(Integer relativeId) {
        return staffRelativeDao.findById(relativeId);
    }

    @Override
    public int add(StaffRelative relative) {
        return staffRelativeDao.insert(relative);
    }

    @Override
    public int update(StaffRelative relative) {
        return staffRelativeDao.update(relative);
    }

    @Override
    public int delete(Integer relativeId) {
        return staffRelativeDao.delete(relativeId);
    }
}