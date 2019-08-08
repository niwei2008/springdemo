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

    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name, @RequestParam int para1,  @RequestParam int para2){

        if(null==name){
            name="boy";
        }
//        //tag必须成对出现，也就是偶数个
//        Counter counter = Counter.builder("counter")
//            .tag("counter", "counter")
//            .description("counter")
//            .register(new SimpleMeterRegistry());
//        counter.increment();
//        counter.increment(2D);
//        System.out.println(counter.count());
//        System.out.println(counter.measure());
//        //全局静态方法
//        Metrics.addRegistry(new SimpleMeterRegistry());
//        counter = Metrics.counter("counter", "counter", "counter");
//        counter.increment(10086D);
//        counter.increment(10087D);
//        System.out.println(counter.count());
//        System.out.println(counter.measure());

        return "hello world     name:" +name+"    para1:"+ para1 +"    para2:"+ para2;
    }


}
