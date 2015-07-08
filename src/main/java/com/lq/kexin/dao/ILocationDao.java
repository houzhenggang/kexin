package com.lq.kexin.dao;

import com.lq.kexin.entity.Location;

import java.util.List;

public interface ILocationDao {

    int addLocation(Location location);

    List<Location> selectLocationsByUserID(int userId);
}
