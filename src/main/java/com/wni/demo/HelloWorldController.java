package com.wni.demo;

import org.springframework.web.bind.annotation.*;

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

        return "hello world     name:" +name+"    para1:"+ para1 +"    para2:"+ para2;
    }


}