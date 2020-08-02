package com.example.arabinda.SpringBasics.Mappers;

import com.example.arabinda.SpringBasics.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from Users where username = #{username}")
    User getUser(String username);


    @Insert("Insert into Users(username, salt, password, firstname, lastname) values(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insertUser(User user);

}
