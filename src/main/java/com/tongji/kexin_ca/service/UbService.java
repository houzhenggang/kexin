package com.tongji.kexin_ca.service;

import com.tongji.kexin_ca.dao.IUbResultDao;
import com.tongji.kexin_ca.entity.UbResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UbService {

    @Resource
    IUbResultDao ubResultDao;

    public List<UbResult> getAllUbResult() {
        return ubResultDao.getUbResultAfterGivenTime(Timestamp.valueOf("1980-01-01 00:00:00"));
    }

    public List<UbResult> getNewUbResultList(Timestamp startTime) {
        return ubResultDao.getUbResultAfterGivenTime(startTime);
    }
}
