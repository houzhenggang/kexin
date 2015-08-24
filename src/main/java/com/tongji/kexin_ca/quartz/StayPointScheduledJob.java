package com.tongji.kexin_ca.quartz;


import com.tongji.kexin_ca.service.StayPointService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StayPointScheduledJob extends QuartzJobBean {

    private StayPointService stayPointService;

    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        stayPointService.stayPointDetection();
    }

    public void setStayPointService(StayPointService object) {
        this.stayPointService = object;
    }
}