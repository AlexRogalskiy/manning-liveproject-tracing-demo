package com.zhaohuabing.demo;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {
    private static String[] tracingHeaders = {"x-request-id","x-b3-traceid","x-b3-spanid","x-b3-parentspanid","x-b3-sampled",
            "x-b3-flags","x-ot-span-context"};

    public static void copyTracingHeaders(HttpHeaders source, HttpHeaders dest) {
        for (int i = 0; i < tracingHeaders.length; i++) {
            dest.add(tracingHeaders[i],source.getFirst(tracingHeaders[i]));
        }
    }
}
