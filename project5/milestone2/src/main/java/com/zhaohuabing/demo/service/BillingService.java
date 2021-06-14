package com.zhaohuabing.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Huabing Zhao
 */
@RestController
public class BillingService {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/payment")
    public String payment(@RequestHeader HttpHeaders headers) {
        // Add a random delay to the service
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Your order has been paid!";
    }
}
