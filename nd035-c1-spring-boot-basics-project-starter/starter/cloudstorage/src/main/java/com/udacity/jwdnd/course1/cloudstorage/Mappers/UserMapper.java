package com.udacity.jwdnd.course1.cloudstorage.Mappers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from Users where username = #{username}")
    Users getUser(String username);


    @Insert("Insert into Users(username, salt, password, firstname, lastname) values(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insertUser(Users user);
}
