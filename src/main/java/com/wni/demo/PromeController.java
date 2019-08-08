package com.wni.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niwei on 2019/8/8.
 */
@RestController
public class PromeController {

    static Counter counter = null;

    static Gauge gauge =null;

    static {
        Counter.builder("eureka.myspringboot.http.requests")
            .tag("uri", "/api/users")
            .description("counter")
            .register(new SimpleMeterRegistry());
        counter = Metrics.counter("eureka.myspringboot.http.requests", "uri", "/api/users");

        AtomicInteger atomicInteger = new AtomicInteger(3);
        gauge = Gauge.builder("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get)
            .tag("gaugetag1", "gaugetag2")
            .description("gauge")
            .register(new SimpleMeterRegistry());

    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(){
        counter.increment();
        String result ="counter.count："+ counter.count() +", counter.measure："+counter.measure();
        System.out.println(result);
        return result;
    }

    @RequestMapping("/gauge")
    @ResponseBody
    public String gauge(){
        String result = "gauge.value："+ gauge.value()+", gauge.measure：" + gauge.measure();
        System.out.println(result);

        AtomicInteger atomicInteger = new AtomicInteger( (int)gauge.value());
        atomicInteger.addAndGet(5);
        Metrics.gauge("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get);

        result = "gauge.value："+ gauge.value()+", gauge.measure：" + gauge.measure();
        System.out.println(result);
        return result;

    }


}
