package com.tongji.kexin_ca.dao;

import com.tongji.kexin_ca.entity.UbResult;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IUbResultDao {
    int insert(UbResult record);

    int insertSelective(UbResult record);

    List<UbResult> getUbResultSelectively(@Param("userId") int userId, @Param("startTime") Date startTime);
}