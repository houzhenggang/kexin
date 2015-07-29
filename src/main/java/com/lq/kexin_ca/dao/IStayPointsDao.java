package com.lq.kexin_ca.dao;

import com.lq.kexin_ca.entity.StayPoints;
import com.lq.kexin_ca.entity.StayPointsExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IStayPointsDao {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int insert(StayPoints record);

    int insertSelective(StayPoints record);

    List<StayPoints> selectByExample(StayPointsExample example);

    StayPoints selectByPrimaryKey(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int updateByPrimaryKeySelective(StayPoints record);

    int updateByPrimaryKey(StayPoints record);
}