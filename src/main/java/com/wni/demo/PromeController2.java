package com.wni.demo;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by niwei on 2019/8/8.
 */
@RestController
public class PromeController2 {

    static final Counter requests = Counter.build()
        .name("requests_total").help("Total requests.").register();

    static final Gauge inprogressRequests =
        Gauge.build().name("inprogress_requests").help("Inprogress requests.").register();

    @RequestMapping("/count2")
    void processRequest1() {
        requests.inc();
        System.out.println(requests.get());
        // Your code here.
    }

    @RequestMapping("/gauge2")
    void processRequest2() {
        //inprogressRequests.set(200);
        inprogressRequests.inc();
        // Your code here.
        //inprogressRequests.dec();
        System.out.println(inprogressRequests.get());
    }


}
