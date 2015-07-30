package com.tongji.kexin_ca.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class StayPointDetectionServiceTest {

    StayPointDetectionService service;

    @Before
    public void setUp() throws Exception {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("spring-config.xml");
        service = (StayPointDetectionService) context.getBean("stayPointDetectionService");
        service.setTs(Timestamp.valueOf(LocalDateTime.of(2015, 7, 30, 10, 0, 0)));
    }

    @Test
    public void testStayPointDetection() throws Exception {

        service.stayPointDetection();
    }
}