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
    public String userLocationMonitor(int userId, ModelMap model) {
        final List<UbResult> ubResultList = ubService.getAllUbResult(userId);
        model.addAttribute("ubResultList", ubResultList);
        model.addAttribute("dateTool", new DateTool());
        model.addAttribute("userId", userId);
        return "monitor/UbMonitor";
    }

    @RequestMapping(value = "/display/newUbResult")
    @ResponseBody
    public List<UbResult> getNewUploadedLocation(int userId, String stringStartTime) {
        final Timestamp startTime = Timestamp.valueOf(stringStartTime);
        System.out.println("startTime: " + startTime);
        final List<UbResult> newUbResultList = ubService.getNewUbResultList(userId, startTime);
        System.out.println(newUbResultList);
        return newUbResultList;

    }

}
