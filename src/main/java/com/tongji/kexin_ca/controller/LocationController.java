package com.tongji.kexin_ca.controller;

import com.tongji.kexin_ca.dto.LocationDTO;
import com.tongji.kexin_ca.entity.Location;
import com.tongji.kexin_ca.entity.StayPoints;
import com.tongji.kexin_ca.service.LocationService;
import com.tongji.kexin_ca.service.StayPointService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Resource
    private LocationService locationService;

    @Resource
    private StayPointService stayPointService;

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

    @RequestMapping(value = "/display/monitor")
    public String userLocationMonitor(ModelMap model) {
        final List<Location> locationList = locationService.getAllLocations();
        model.addAttribute("locationList", locationList);

        final List<StayPoints> StaypointsList = stayPointService.getAllStaypoints();

        model.addAttribute("stayPointList", StaypointsList);

        model.addAttribute("dateTool", new DateTool());
        return "Location/UserLocationMonitor";
    }

    @RequestMapping(value = "/display/newUploadedLocation")
    @ResponseBody
    public List<Location> getNewUploadedLocation(String stringStartTime) {
        final Timestamp startTime = Timestamp.valueOf(stringStartTime);
        System.out.println("startTime: " + startTime);
        final List<Location> newUploadedLocationList = locationService.getNewUploadedLocation(startTime);
        System.out.println(newUploadedLocationList);
        return newUploadedLocationList;

    }

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }


}
