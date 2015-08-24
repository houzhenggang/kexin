package com.tongji.kexin_ca.quartz;

import com.tongji.kexin_ca.util.TokenManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TokenDeleteScheduledJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TokenManager.deleteExpiredTokens();
    }
}
