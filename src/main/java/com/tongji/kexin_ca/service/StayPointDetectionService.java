package com.tongji.kexin_ca.service;

import com.tongji.kexin_ca.dao.ILocationDao;
import com.tongji.kexin_ca.dao.IStayPointsDao;
import com.tongji.kexin_ca.entity.Location;
import com.tongji.kexin_ca.entity.StayPoints;
import com.tongji.kexin_ca.util.Distance;
import com.tongji.kexin_ca.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StayPointDetectionService {

    /**
     * gpslogsdao对应 你那边的gpslog获取dao
     */
    @Autowired
    ILocationDao locationDao;


    /**
     * staypoint dao 对应 保存staypoint的dao
     */
    @Autowired
    IStayPointsDao stayPointDao;
    /**
     * 逗留点距离阈值 小于 单位：米
     */
    long distThreh = 50;
    /**
     * 逗留点时间阈值 大于 单位：毫秒
     */
    long timeThreh = 60 * 30 * 1000;


    public Timestamp ts ;

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public ILocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public IStayPointsDao getStayPointDao() {
        return stayPointDao;
    }

    public void setStayPointDao(IStayPointsDao stayPointDao) {
        this.stayPointDao = stayPointDao;
    }

    public void stayPointDetection() {
        List<Integer> idslist = this.getLocationDao().getAllUserIds();
        System.out.println(idslist.size());
        for (Integer userId : idslist) {


            List<Location> list = this.getLocationDao().selectLocationByUserIDandDate(userId, ts);
            boolean tag = false;
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    System.out.println(Time.CalTimeDiff(list.get(j).getTime(), list.get(i).getTime()));
                    if (Math.abs(Time.CalTimeDiff(list.get(j).getTime(), list.get(i).getTime())) > timeThreh) {
                        double distance = Distance.CalDistance(list.get(j).getLatitude(), list.get(j).getLongitude(), list.get(i).getLatitude(), list.get(i).getLongitude());
                        if (distance <= distThreh) {
                            double meanlat = (list.get(j).getLatitude() + list.get(i).getLongitude()) / 2;
                            double meanlng = (list.get(j).getLongitude() + list.get(i).getLongitude()) / 2;
                            StayPoints sp = new StayPoints();
                            sp.setStartTime(list.get(i).getTime());
                            sp.setEndTime(list.get(j).getTime());
                            sp.setLat(meanlat);
                            sp.setLon(meanlng);
                            sp.setUserId(list.get(i).getUserId());
                            System.out.println("x1");
                            this.getStayPointDao().insert(sp);
                            i = j + 1;
                            break;
                        } else {
                            System.out.println("x2");
                            i = i + 1;
                            break;
                        }
                    }
                }
            }
        }

    }

}
