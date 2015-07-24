package com.lq.kexin_ca.dao;

import com.lq.kexin_ca.entity.Location;
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
