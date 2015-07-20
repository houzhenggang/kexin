package com.lq.kexin.service;

import com.lq.kexin.entity.Location;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LocationServiceTest {
    LocationService service;

    @Before
    public void setUp() throws Exception {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("spring-config.xml");
        service = (LocationService) context.getBean("locationService");
    }

    @Test
    public void testSelectLocationByUserIDandDate() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2015, 7, 8, 0, 0);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        List<Location> list = service.selectLocationByUserIDandDate(1, timestamp);
        System.out.println(list);
        assertEquals(list.size(), 2);

    }
}