package com.lq.kexin_ca.controller;

import com.lq.kexin_ca.entity.Location;
import com.lq.kexin_ca.entity.LocationDTO;
import com.lq.kexin_ca.service.LocationService;
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
    @ResponseBody
    public String uploadLocation(@RequestBody LocationDTO location) {
        System.out.println(location);
        final int result = locationService.saveLocation(location);
        if (result == 0) return "UploadSuccess";
        else if (result == 1) return "LocationException";
        return "UploadFail";
    }

    @RequestMapping(value = "/list/{userId}")
    @ResponseBody
    public List<Location> selectLocationsByUserID(@PathVariable int userId) {
        return locationService.selectLocationsByUserID(userId);
    }

    @RequestMapping(value = "/count/{userId}")
    @ResponseBody
    public String countUserLocation(@PathVariable int userId) {
        return String.valueOf(locationService.countUserLocation(userId));

    }

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }


}
