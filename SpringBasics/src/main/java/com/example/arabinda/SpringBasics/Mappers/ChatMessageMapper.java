package com.example.arabinda.SpringBasics.Mappers;

import com.example.arabinda.SpringBasics.Model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageMapper {

    @Select("Select * from Messages where userName = #{userName}")
    List<ChatMessage> getMessage(String userName);

    @Insert("Insert into Messages (userName, userMessage) values (#{userName}, #{userMessage})")
    @Options(useGeneratedKeys = true, keyProperty = "messageid")
    int insertMessage(ChatMessage message);
}
