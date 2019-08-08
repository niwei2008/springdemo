package com.wni.demo;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by niwei on 2019/8/8.
 */
@RestController
public class PromeController2 {

    static final Counter requests = Counter.build()
        .name("myspringboot_prome2_requests_total").help("Total requests.").register();

    static final Gauge inprogressRequests =
        Gauge.build().name("myspringboot_prome2_inprogress_requests").help("Inprogress requests.").register();

    @RequestMapping("/count2")
    @ResponseBody
    public String processRequest1() {
        requests.inc();

        // Your code here.

        String result = "count2 result:" + requests.get();
        System.out.println(result);
        return result;
    }

    @RequestMapping("/gauge2")
    @ResponseBody
    public String  processRequest2() {
        //inprogressRequests.set(200);
        //inprogressRequests.dec();
        inprogressRequests.inc();

        // Your code here.

        String result = "gauge2 result:" + inprogressRequests.get();
        System.out.println(result);
        return result;
    }


}
