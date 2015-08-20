package com.tongji.kexin_ca.util;

public class Distance {
	
	public static double earthRadius = 6371009;
	 
	 
	 private static double rad(double d)
	 {
	       return d * Math.PI / 180.0;
	 }

	 /**
	  * 
	  * 计算两gps坐标间的距离，单位为米
	  * 
	  * @param 
	  */
	 public static double CalDistance(double latitude1,double longtitude1,double latitude2,double longtitude2)
	 {
		 	double distance = 0.0;
			distance = 2 * Distance.earthRadius * Math.asin(Math.sqrt(Math.pow(Math.sin((rad(latitude1)-rad(latitude2))/2), 2)+
					Math.cos(rad(latitude2))*Math.cos(rad(latitude1))*Math.pow(Math.sin((rad(longtitude1)-rad(longtitude2))/2), 2)));
			return distance;
	 }
	 
}
