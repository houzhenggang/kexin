package com.lq.kexin.dao;

import com.lq.kexin.entity.Location;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.sql.Timestamp;
import java.util.List;

public interface ILocationDao {

    int addLocation(Location location) throws DataAccessException;

    List<Location> selectLocationsByUserID(int userId);

    int countUserLocation(int userId);

    List<Location> selectLocationByUserIDandDate(@Param("userId") int userId, @Param("date") Timestamp date);
}
