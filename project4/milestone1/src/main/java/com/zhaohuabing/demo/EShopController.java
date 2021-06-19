package com.zhaohuabing.demo;

import jdk.javadoc.internal.doclets.formats.html.markup.Head;
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
public class EShopController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        String result = "";
        try {
            // Use HTTP GET in this demo. In a real world use case,We should use HTTP POST instead.
            // The three services are bundled in one jar for simplicity. To make it work,define three services in Kubernets.
            HttpHeaders outboundHeaders = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>("",outboundHeaders);
            result += restTemplate.exchange("http://inventory:8080/createOrder", HttpMethod.GET, entity, String.class).getBody();
            result += "<BR>";
            result += restTemplate.exchange("http://billing:8080/payment", HttpMethod.GET, entity, String.class).getBody();
            result += "<BR>";
            result += restTemplate.exchange("http://delivery:8080/arrangeDelivery", HttpMethod.GET, entity, String.class).getBody();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
