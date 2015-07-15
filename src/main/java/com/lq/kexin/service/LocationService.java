package com.lq.kexin.service;

import com.lq.kexin.dao.ILocationDao;
import com.lq.kexin.entity.Location;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public ILocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }
}
