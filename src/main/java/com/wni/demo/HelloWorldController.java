package com.wni.demo;

import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Created by niwei on 2019/4/24.
 */
@RestController
public class HelloWorldController {

    static {
        Counter counter = Counter.builder("counter")
            .tag("counter", "counter")
            .description("counter")
            .register(new SimpleMeterRegistry());
        Metrics.addRegistry(new SimpleMeterRegistry());
    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(){
        Counter counter = Metrics.counter("counter", "counter", "counter");
        counter.increment();
        counter.increment(2D);
        counter.increment(3);
        System.out.println(counter.count());
        System.out.println(counter.measure());
        return "counter.count()："+ counter.count() +", counter.measure()："+counter.measure();
    }



    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name, @RequestParam int para1,  @RequestParam int para2){

        if(null==name){
            name="boy";
        }
        //tag必须成对出现，也就是偶数个

        Counter counter = Metrics.counter("counter", "counter", "counter");
        counter.increment();
        counter.increment(2D);
        counter.increment(3);
        System.out.println(counter.count());
        System.out.println(counter.measure());
        //全局静态方法

        return "hello world     name:" +name+"    para1:"+ para1 +"    para2:"+ para2;
    }


}
