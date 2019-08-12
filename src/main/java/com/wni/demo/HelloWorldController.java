package com.wni.demo;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niwei on 2019/4/24.
 */
@RestController
public class HelloWorldController {
    private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    static Counter counter = null;

    static AtomicInteger atomicInteger = null;
    static Gauge gauge =null;

    static {
         Counter.builder("eureka.myspringboot.http.requests")
            .tag("uri", "/api/users")
            .description("counter")
            .register(new SimpleMeterRegistry());
         counter = Metrics.counter("eureka.myspringboot.http.requests", "uri", "/api/users");

         Counter.builder("eureka.myspringboot.http.requests2")
            .tag("uri", "/api/users2")
            .description("counter")
            .register(new SimpleMeterRegistry());


         atomicInteger = new AtomicInteger();
         gauge = Gauge.builder("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get)
            .tag("gaugetag1", "gaugetag2")
            .description("gauge")
            .register(new SimpleMeterRegistry());

    }

    @RequestMapping("/index/count")
    @ResponseBody
    public String count(){
        counter.increment();
        counter.increment(2D);
        counter.increment(3);
        logger.info("" + counter.count());
        logger.info("" + counter.measure());

        Counter counter2 = Metrics.counter("eureka.myspringboot.http.requests2", "uri", "/api/users22");
        counter2.increment();

        return "counter.count："+ counter.count() +", counter.measure："+counter.measure();
    }
    @RequestMapping("/index/gauge")
    @ResponseBody
    public String gauge(){
        atomicInteger.addAndGet(5);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        atomicInteger.decrementAndGet();
        logger.info("" + gauge.value());
        logger.info("" + gauge.measure());

        AtomicInteger data = Metrics.gauge("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get);

        return "gauge.value："+ gauge.value()+", gauge.measure：" + gauge.measure() +", data:"+ data;

    }


    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name, @RequestParam int para1,  @RequestParam int para2){

        if(null==name){
            name="boy";
        }

        return "hello world     name:" +name+"    para1:"+ para1 +"    para2:"+ para2;
    }


}
