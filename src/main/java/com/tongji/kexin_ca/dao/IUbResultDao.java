package com.tongji.kexin_ca.dao;

import com.tongji.kexin_ca.entity.UbResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IUbResultDao {
    int insert(UbResult record);

    int insertSelective(UbResult record);

    List<UbResult> getUbResultAfterGivenTime(Date timestamp);
}