package com.lq.kexin.service;

import com.lq.kexin.dao.ILocationDao;
import com.lq.kexin.entity.Location;
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
        return locationDao.addLocation(location);
    }

    public ILocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }
}
