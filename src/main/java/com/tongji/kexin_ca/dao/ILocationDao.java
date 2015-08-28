package com.tongji.kexin_ca.dao;

import com.tongji.kexin_ca.entity.Location;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.sql.Timestamp;
import java.util.List;

public interface ILocationDao {

    int addLocation(Location location) throws DataAccessException;

    List<Location> selectLocationsByUserID(@Param("userId") int userId);

    int countUserLocation(@Param("userId") int userId);

    List<Location> selectLocationByUserIDandDate(@Param("userId") int userId, @Param("date") Timestamp date);

    List<Integer> getAllUserIds();

    List<Location> getLocationsSelectively(@Param("userId") int userId, @Param("startTime") Timestamp startTime);
}
