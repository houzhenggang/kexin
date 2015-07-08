package com.lq.kexin.controller;

import com.lq.kexin.entity.Location;
import com.lq.kexin.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Resource
    private LocationService locationService;


    @RequestMapping(value = "/upload")
    public String uploadLocation(@RequestBody Location location) {
        System.out.println(location);
        locationService.saveLocation(location);
        return "Location/UploadSuccess";
    }

    @RequestMapping(value = "/list/{userId}")
    @ResponseBody
    public List<Location> selectLocationsByUserID(@PathVariable int userId) {
        return locationService.selectLocationsByUserID(userId);
    }


    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }


}
