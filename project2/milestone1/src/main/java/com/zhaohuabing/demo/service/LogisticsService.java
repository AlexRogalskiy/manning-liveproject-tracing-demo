package com.zhaohuabing.demo.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Huabing Zhao
 */
@Service
public class LogisticsService {

    @Autowired 
    private Tracer tracer;

    public String transport(Span rootSpan) {
        Span span = tracer.buildSpan("transport").asChildOf(rootSpan).start();
        // Add a random delay to the service
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            span.finish();
        }

        return "Your order is on the way!";
    }
}