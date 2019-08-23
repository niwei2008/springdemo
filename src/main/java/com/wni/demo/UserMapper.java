package com.wni.demo;

import com.wni.demo.model.UserObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by niwei on 2019/8/23.
 */
@Mapper
public interface UserMapper {



    @Select("SELECT * FROM user_object WHERE id = #{id}")
    @Results({
        @Result(property = "userName",  column = "user_name"),
        @Result(property = "userPhoneNumber", column = "user_phone_number")
    })
    UserObject getOne(Long id);



}
