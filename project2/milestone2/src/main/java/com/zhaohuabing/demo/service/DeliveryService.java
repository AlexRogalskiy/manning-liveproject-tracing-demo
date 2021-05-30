package com.zhaohuabing.demo.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Huabing Zhao
 */
@Service
public class DeliveryService {

    @Autowired
    private Tracer tracer;

    @Autowired
    private LogisticsService logisticsService;

    public String arrangeDelivery() {
        Span parent = tracer.scopeManager().activeSpan();
        Span span = tracer.buildSpan("arrangeDelivery").start();
        String result = "";
        try {
            // set active span to the current span before calling logisticsService
            tracer.scopeManager().activate(span);
            Thread.sleep((long) (Math.random() * 1000));
            result = logisticsService.transport();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            span.finish();
            // set active span back to the parent span
            tracer.scopeManager().activate(parent);
            return result + "\r\nYour order is delivered!";
        }
    }
}
