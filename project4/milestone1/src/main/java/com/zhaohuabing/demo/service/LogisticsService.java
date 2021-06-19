package com.zhaohuabing.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class LogisticsService {
    @RequestMapping(value = "/transport")
    public String transport(@RequestHeader HttpHeaders headers) {
        // Add a random delay to the service
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Your order is on the way!";
    }
}
