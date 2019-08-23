package com.wni.demo;

import com.wni.demo.mapper.MyUserMapper;
import com.wni.demo.mapper.UserObjectMapper;
import com.wni.demo.model.UserObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by niwei on 2019/8/23.
 */
@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyUserMapperTest {

    @Autowired
    private MyUserMapper myUserMapper;

    @Autowired
    private UserObjectMapper userObjectMapper;

    @Test
    public void testQuery() throws Exception {
        System.out.println("\n####################testQuery###################");

        UserObject user = myUserMapper.getOne(10500L);
        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());

    }

    @Test
    public void testQuery2() throws Exception {
        System.out.println("\n####################testQuery2###################");

        UserObject user = userObjectMapper.selectByPrimaryKey(10500);
        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());
    }





}
