package com.tongji.kexin_ca.service;

import com.tongji.kexin_ca.entity.Location;
import com.tongji.kexin_ca.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LocationServiceTest {
    static LocationService service;

    public static void setUp() {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("spring-config.xml");
        service = (LocationService) context.getBean("locationService");
    }

    public static void testSelectLocationByUserIDandDate() {
        setUp();
        LocalDateTime localDateTime = LocalDateTime.of(2015, 7, 8, 0, 0);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        List<Location> list = service.selectLocationByUserIDandDate(1, timestamp);
        System.out.println(list);
        assertEquals(list.size(), 2);

    }

    public static void main(String[] args) {
        testSelectLocationByUserIDandDate();
    }
}