package com.tongji.kexin_ca.controller;


import com.tongji.kexin_ca.entity.UbResult;
import com.tongji.kexin_ca.service.UbService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/ub")
public class UserBehaviorController {
    @Resource
    UbService ubService;


    @RequestMapping(value = "/display/monitor")
    public String userLocationMonitor(ModelMap model) {
        final List<UbResult> ubResultList = ubService.getAllUbResult();
        model.addAttribute("ubResultList", ubResultList);
        model.addAttribute("dateTool", new DateTool());
        return "UserBehavior/ubMonitor";
    }

    @RequestMapping(value = "/display/newUbResult")
    @ResponseBody
    public List<UbResult> getNewUploadedLocation(String stringStartTime) {
        final Timestamp startTime = Timestamp.valueOf(stringStartTime);
        System.out.println("startTime: " + startTime);
        final List<UbResult> newUbResultList = ubService.getNewUbResultList(startTime);
        System.out.println(newUbResultList);
        return newUbResultList;

    }

}
