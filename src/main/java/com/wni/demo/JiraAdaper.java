package com.wni.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by niwei on 2019/7/22.
 */
@RestController
public class JiraAdaper {
    @RequestMapping("/jira/{name}")
    @ResponseBody
    public String index(HttpServletRequest request, @PathVariable String name, @RequestParam int issue_id,  @RequestParam String issue_key, @RequestBody
    Map o){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName() + " : "+auth.isAuthenticated());
        if (null != o ){
            System.out.println(o.toString());
        }

        if(null==name){
            name="jira";
        }
        String method = request.getMethod();

        String result ="JiraAdaper method:" +method +  ", name:" +name+"    issue_id:"+ issue_id +"    issue_key:"+ issue_key;
        System.out.println(result);
        return result;
    }

}
