package com.zhaohuabing.demo;

import com.zhaohuabing.demo.service.BillingService;
import com.zhaohuabing.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.opentracing.Tracer;
import io.opentracing.Span;
import com.zhaohuabing.demo.service.InventoryService;

/**
 * Huabing Zhao
 */
@RestController
public class EShopController {
    @Autowired
    private Tracer tracer;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        Span span = tracer.buildSpan("checkout").start();
        String result= "";
        result = result + inventoryService.createOrder(span) +System.lineSeparator();
        result = result + billingService.payment(span)+System.lineSeparator();
        result += deliveryService.arrangeDelivery(span);
        span.finish();
        return result;
    }
}
