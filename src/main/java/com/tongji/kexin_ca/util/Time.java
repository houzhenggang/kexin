package com.tongji.kexin_ca.util;

import java.sql.Timestamp;

public class Time {


    /**
     * @param Timestamp
     * @author xubing
     * @message 计算两时间戳之间的毫秒差
     */
    public static long CalTimeDiff(Timestamp timestamp, Timestamp timestamp2) {
        long ts = timestamp2.getTime() - timestamp.getTime();
        return ts;
    }
}
