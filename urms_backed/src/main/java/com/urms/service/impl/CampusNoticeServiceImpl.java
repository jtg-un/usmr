package com.urms.service.impl;

import com.urms.dao.CampusNoticeDao;
import com.urms.entity.CampusNotice;
import com.urms.service.CampusNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 校园公告Service实现类
 */
@Service
public class CampusNoticeServiceImpl implements CampusNoticeService {

    @Autowired
    private CampusNoticeDao campusNoticeDao;

    @Override
    public List<CampusNotice> findAll() {
        return campusNoticeDao.findAll();
    }

    @Override
    public CampusNotice findById(Integer noticeId) {
        return campusNoticeDao.findById(noticeId);
    }

    @Override
    public int publish(CampusNotice notice) {
        return campusNoticeDao.insert(notice);
    }

    @Override
    public int delete(Integer noticeId) {
        return campusNoticeDao.delete(noticeId);
    }
}