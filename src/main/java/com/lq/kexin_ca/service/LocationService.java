package com.lq.kexin_ca.service;

import com.lq.kexin_ca.dao.ILocationDao;
import com.lq.kexin_ca.entity.Location;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class LocationService {

    @Resource
    private ILocationDao locationDao;

    public List<Location> selectLocationsByUserID(int userId) {
        return locationDao.selectLocationsByUserID(userId);
    }

    public int saveLocation(Location location) {
        int result;
        try {
            result = locationDao.addLocation(location);
        } catch (DataAccessException dae) {
            result = 0;
            dae.printStackTrace();
        }
        return result;
    }

    public int countUserLocation(int userId) {
        return locationDao.countUserLocation(userId);
    }

    public List<Location> selectLocationByUserIDandDate(int userId, Timestamp date) {
        return locationDao.selectLocationByUserIDandDate(userId, date);
    }

    public ILocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }
}
