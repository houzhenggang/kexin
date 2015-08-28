package com.tongji.kexin_ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MonitorController {

    @RequestMapping("/monitor")
    public String showMonitor() {
        return "monitor/MonitorIndex";
    }
}
