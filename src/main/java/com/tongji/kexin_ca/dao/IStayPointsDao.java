package com.tongji.kexin_ca.dao;

import com.tongji.kexin_ca.entity.StayPoints;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IStayPointsDao {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int insert(StayPoints record);

    int insertSelective(StayPoints record);

    List<StayPoints> selectByExample(StayPointsExample example);

    StayPoints selectByPrimaryKey(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int updateByPrimaryKeySelective(StayPoints record);

    int updateByPrimaryKey(StayPoints record);

    List<StayPoints> getAllStayPoints(@Param("userId") int userId);

}