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

    public String arrangeDelivery(Span parent) {
        Span span = tracer.buildSpan("arrangeDelivery").asChildOf(parent).start();
	String result = "";
        try {
            // Add a random delay to the service
            Thread.sleep((long) (Math.random() * 1000));
	    result = logisticsService.transport(span);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            span.finish();
        }
        return result + "\r\nYour order is delivered!";
    }
}
