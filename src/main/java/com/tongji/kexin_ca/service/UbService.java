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

    public List<UbResult> getAllUbResult(int userId) {
        return ubResultDao.getUbResultSelectively(userId, null);
    }

    public List<UbResult> getNewUbResultList(int userId, Timestamp startTime) {
        return ubResultDao.getUbResultSelectively(userId, startTime);
    }
}
