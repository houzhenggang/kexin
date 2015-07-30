package com.tongji.kexin_ca.util;

import java.sql.Date;
import java.sql.Timestamp;

public class Time {

	/**
	 * ����ʱ���
	 * 
	 * @param 
	 * 
	 * @return ��ʱ��� < 1200s,����0����ʱ��� > 1200s,����1
	 */
	public static long CalTimeDiff(Timestamp timestamp, Timestamp timestamp2)
	{ 		
	    long ts = timestamp2.getTime() -timestamp.getTime();	    
	    return ts;
	}
}
