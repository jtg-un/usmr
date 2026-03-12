package com.urms.service.impl;

import com.urms.dao.SalaryRecordDao;
import com.urms.entity.SalaryRecord;
import com.urms.service.SalaryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 工资记录Service实现类
 */
@Service
public class SalaryRecordServiceImpl implements SalaryRecordService {

    @Autowired
    private SalaryRecordDao salaryRecordDao;

    @Override
    public List<SalaryRecord> findByStaffId(Integer staffId) {
        return salaryRecordDao.findByStaffId(staffId);
    }

    @Override
    public List<SalaryRecord> findAll() {
        return salaryRecordDao.findAll();
    }

    @Override
    public SalaryRecord findById(Integer salaryId) {
        return salaryRecordDao.findById(salaryId);
    }

    @Override
    public int add(SalaryRecord record) {
        record.setStatus(0);
        return salaryRecordDao.insert(record);
    }

    @Override
    public int update(SalaryRecord record) {
        return salaryRecordDao.update(record);
    }

    @Override
    public int delete(Integer salaryId) {
        return salaryRecordDao.delete(salaryId);
    }
}