package com.wni.demo;

import com.wni.demo.mapper.MyUserMapper;
import com.wni.demo.mapper.UserObjectMapper;
import com.wni.demo.model.UserObject;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niwei on 2019/8/8.
 */
@RequestMapping("/demo/api")
@RestController
public class PromeController {

    static Counter counter = null;

    static AtomicInteger atomicInteger = null;
    static Gauge gauge =null;

    static {
        Counter.builder("eureka.myspringboot.http.requests")
            .tag("uri", "/api/users")
            .description("counter")
            .register(new SimpleMeterRegistry());
        counter = Metrics.counter("eureka.myspringboot.http.requests", "uri", "/api/users");

        atomicInteger = new AtomicInteger(3);
        gauge = Gauge.builder("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get)
            .tag("gaugetag1", "gaugetag2")
            .description("gauge")
            .register(new SimpleMeterRegistry());

    }

    @Autowired
    private MyUserMapper myUserMapper;

    @RequestMapping("/count")
    @ResponseBody
    public String count(){
        counter.increment();
        String result ="counter.count："+ counter.count() +", counter.measure："+counter.measure();
        System.out.println(result);

        UserObject user = myUserMapper.getOne(10500L);
        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());
        return result;
    }

    @Autowired
    private UserObjectMapper userObjectMapper;

    @RequestMapping("/gauge")
    @ResponseBody
    public String gauge(){
        String result = "start gauge.value："+ gauge.value()+", gauge.measure：" + gauge.measure();
        System.out.println(result);

        //AtomicInteger atomicInteger2 = new AtomicInteger( (int)gauge.value());
        atomicInteger.addAndGet(5);
        Metrics.gauge("eureka.myspringboot.mygauge", atomicInteger, AtomicInteger::get);

        result = "end   gauge.value："+ gauge.value()+", gauge.measure：" + gauge.measure();
        System.out.println(result);

        UserObject user = userObjectMapper.selectByPrimaryKey(10500);
        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());
        return result;

    }


}
