package com.wni.demo;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
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

    //private static PrometheusMeterRegistry registry = null;

    static Counter counter = null;
    static AtomicInteger atomicInteger = null;
    static Gauge gauge =null;

    static {


        //registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

         Counter counter = Counter.builder("http.requests")
            .tag("uri", "/api/users")
            .description("counter")
            .register(new SimpleMeterRegistry());

         Counter.builder("http.requests2")
            .tag("uri", "/api/users2")
            .description("counter")
            .register(new SimpleMeterRegistry());

//        Metrics.addRegistry(new SimpleMeterRegistry());

         atomicInteger = new AtomicInteger();
         gauge = Gauge.builder("mygauge", atomicInteger, AtomicInteger::get)
            .tag("gaugetag1", "gaugetag2")
            .description("gauge")
            .register(new SimpleMeterRegistry());



        //        AtomicInteger atomicInteger = new AtomicInteger();
//        Gauge gauge = Gauge.builder("gauge", atomicInteger, AtomicInteger::get)
//            .tag("gauge", "gauge")
//            .description("gauge")
//            .register(new SimpleMeterRegistry());
//        Metrics.addRegistry(new SimpleMeterRegistry());

    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(){
        //Counter counter = Metrics.counter("http.requests", "uri", "/api/users");
        //Counter counter = registry.counter("counter", "counter", "counter");
        counter.increment();
        counter.increment(2D);
        counter.increment(3);
        System.out.println(counter.count());
        System.out.println(counter.measure());

        Counter counter2 = Metrics.counter("http.requests2", "uri", "/api/users22");
        counter2.increment();

        return "counter.count："+ counter.count() +", counter.measure："+counter.measure();
    }
    @RequestMapping("/gauge")
    @ResponseBody
    public String gauge(){
//        AtomicInteger n = registry.gauge("numberGauge", new AtomicInteger(0));
//        n.set(1);
//        n.set(2);


        atomicInteger.addAndGet(5);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        atomicInteger.decrementAndGet();
        System.out.println(gauge.value());
        System.out.println(gauge.measure());

        //Metrics.addRegistry(new SimpleMeterRegistry());
        AtomicInteger data = Metrics.gauge("gauge", atomicInteger, AtomicInteger::get);

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
