package com.lq.kexin_ca.service;

import com.lq.kexin_ca.entity.LocationDTO;
import com.lq.kexin_ca.dao.ILocationDao;
import com.lq.kexin_ca.dao.IStayPointsDao;
import com.lq.kexin_ca.entity.Location;
import com.lq.kexin_ca.entity.StayPoints;
import com.lq.kexin_ca.entity.StayPointsExample;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class LocationService {

    @Resource
    private ILocationDao locationDao;
    @Resource
    private IStayPointsDao stayPointsDao;

    public List<Location> selectLocationsByUserID(int userId) {
        return locationDao.selectLocationsByUserID(userId);
    }

    /**
     * @param locationDTO 存储的位置
     * @return 0 正常，1 位置点异常，-1 错误
     */

    public int saveLocation(LocationDTO locationDTO) {
        int result = 1;//异常
        try {
            StayPointsExample stayPointsExample = new StayPointsExample();
            stayPointsExample.or().andUserIdEqualTo(locationDTO.getUserId());

            List<StayPoints> stayPointsList = stayPointsDao.selectByExample(stayPointsExample);
//            if (stayPointsList.size() == 0) {
//                //启动点聚类
//                result = 1;
//            }
            for (StayPoints stayPoint : stayPointsList) {
                if (LocationCheck(stayPoint.getLat(), stayPoint.getLon(), locationDTO.getLatitude(), locationDTO.getLongitude())) {
                    result = 0;//正常
                    break;
                }
            }
            Location location = new Location(locationDTO, result);
            locationDao.addLocation(location);

        } catch (DataAccessException dae) {
            result = -1;
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

    /**
     * 检测坐标是否正常
     *
     * @return true:坐标点正常，false:坐标点异常
     */
    public static boolean LocationCheck(double latitude1, double longitude1, double latitude2, double longitude2) {
        double a = latitude1 * Math.PI / 180.0 - latitude2 * Math.PI / 180.0;
        double b = longitude1 * Math.PI / 180.0 - longitude2 * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(latitude1 * Math.PI / 180.0)
                * Math.cos(latitude2 * Math.PI / 180.0)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378.137 * 1000;
        s = Math.round(s);
        if (s > 500) {
            return false;
        }
        return true;
    }
}
