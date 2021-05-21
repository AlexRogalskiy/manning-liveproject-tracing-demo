package com.zhaohuabing.demo;

import com.zhaohuabing.demo.service.BillingService;
import com.zhaohuabing.demo.service.DeliveryService;
import io.opentracing.Scope;
import io.opentracing.log.Fields;
import io.opentracing.tag.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.opentracing.Tracer;
import io.opentracing.Span;
import com.zhaohuabing.demo.service.InventoryService;

import java.util.Map;

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
        try  {
            tracer.scopeManager().activate(span);
            result = result + inventoryService.createOrder() + System.lineSeparator();
            result = result + billingService.payment() + System.lineSeparator();
            result += deliveryService.arrangeDelivery();
        } catch (Exception ex) {
            Tags.ERROR.set(span, true);
            span.log(Map.of(Fields.EVENT, "error", Fields.ERROR_OBJECT, ex, Fields.MESSAGE, ex.getMessage()));
        } finally {
            span.finish();
            return result;
        }
    }
}
