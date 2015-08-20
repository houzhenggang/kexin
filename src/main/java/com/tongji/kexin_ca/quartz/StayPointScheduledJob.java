package com.tongji.kexin_ca.quartz;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tongji.kexin_ca.service.StayPointDetectionService

public class StayPointScheduledJob extends QuartzJobBean{

	private StayPointDetectionService stayPointDetectionService;

	protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        stayPointDetectionService.stayPointDetection();
    }

    public void setStayPointDetectionService(StayPointDetectionService object){
    	this.StayPointDetectionService = object;
    }
}