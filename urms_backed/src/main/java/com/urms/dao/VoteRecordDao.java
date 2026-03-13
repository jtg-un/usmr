package com.urms.dao;

import com.urms.entity.VoteRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 投票记录DAO接口
 */
@Mapper
public interface VoteRecordDao {

    /**
     * 添加投票记录
     */
    int insert(VoteRecord record);

    /**
     * 统计用户今日投票次数
     */
    int countTodayVotes(@Param("staffId") Integer staffId);

    /**
     * 检查用户是否已对该活动投票
     */
    int checkVoted(@Param("activityId") Integer activityId, @Param("staffId") Integer staffId);

    /**
     * 获取用户对所有活动的投票记录
     */
    List<VoteRecord> findByStaffId(Integer staffId);
}