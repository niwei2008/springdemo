package com.wni.demo;

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
public class UserMapperTest {

    //    @Autowired
    //    private UserMapper UserMapper;

    @Test
    public void testQuery() throws Exception {
        System.out.println("####################test###################3");
        //        UserObject user = UserMapper.getOne(10500L);
        //        System.out.println(user.getUserName());
        //        System.out.println(user.getUserEmail());
    }

}
