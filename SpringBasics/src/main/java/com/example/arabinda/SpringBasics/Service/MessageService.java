package com.example.arabinda.SpringBasics.Service;

import com.example.arabinda.SpringBasics.Mappers.ChatMessageMapper;
import com.example.arabinda.SpringBasics.Mappers.UserMapper;
import com.example.arabinda.SpringBasics.Model.ChatForm;
import com.example.arabinda.SpringBasics.Model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageService {

    @Autowired
    ChatMessageMapper chatMessageMapper;

    @Autowired
    UserMapper userMapper;


    public void addMessage(ChatMessage chatMessage){
        chatMessageMapper.insertMessage(chatMessage);
    }

    public List<ChatMessage> getListOfMessages(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return chatMessageMapper.getMessage(userName);
    }

    public void getChatMessage(ChatForm chatForm) {
        ChatMessage chatMessage = new ChatMessage();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(chatForm.getMessageType().equalsIgnoreCase("Shout")) {
            chatMessage.setUserName(userName);
            chatMessage.setUserMessage(chatForm.getUserMessage().toUpperCase());
        } else if(chatForm.getMessageType().equalsIgnoreCase("Whisper")){
            chatMessage.setUserName(userName);
            chatMessage.setUserMessage(chatForm.getUserMessage().toLowerCase());
        } else {
            chatMessage.setUserName(userName);
            chatMessage.setUserMessage(chatForm.getUserMessage());
        }
        addMessage(chatMessage);
    }

}
