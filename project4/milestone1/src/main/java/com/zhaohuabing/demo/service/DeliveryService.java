package com.zhaohuabing.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Huabing Zhao
 */
@RestController
public class DeliveryService {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/arrangeDelivery")
    public String arrangeDelivery(@RequestHeader HttpHeaders headers) {
        String result = "";
        try {
            Thread.sleep((long) (Math.random() * 1000));
            HttpHeaders outboundHeaders = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>("",outboundHeaders);
            result += restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result + "<BR>Your order is delivered!";
    }
}
