package com.zhaohuabing.demo;

import com.zhaohuabing.demo.service.BillingService;
import com.zhaohuabing.demo.service.DeliveryService;
import com.zhaohuabing.demo.service.InventoryService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String result = "";
        try {
            tracer.scopeManager().activate(span);
            result = result + inventoryService.createOrder() + System.lineSeparator();
            result = result + billingService.payment() + System.lineSeparator();
            result += deliveryService.arrangeDelivery();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            span.finish();
            return result;
        }
    }
}
