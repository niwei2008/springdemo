package com.wni.demo;

import org.springframework.web.bind.annotation.*;

/**
 * Created by niwei on 2019/7/22.
 */
@RestController
public class JiraAdaper {
    @RequestMapping("/jira/{name}")
    @ResponseBody
    public String index(@PathVariable String name, @RequestParam int issue_id,  @RequestParam String issue_key){

        if(null==name){
            name="jira";
        }
        String result ="JiraAdaper  name:" +name+"    issue_id:"+ issue_id +"    issue_key:"+ issue_key;
        System.out.println(result);
        return result;
    }

}
