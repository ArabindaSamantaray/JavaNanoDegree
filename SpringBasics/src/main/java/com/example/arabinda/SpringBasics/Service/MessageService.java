package com.example.arabinda.SpringBasics.Service;

import com.example.arabinda.SpringBasics.Model.ChatForm;
import com.example.arabinda.SpringBasics.Model.ChatMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageService {

    List<ChatMessage> listOfMessages;

    @PostConstruct
    public void postConstruct(){
        this.listOfMessages = new ArrayList<>();
    }

    public void addMessage(ChatMessage chatMessage){
        this.listOfMessages.add(chatMessage);
    }

    public List<ChatMessage> getListOfMessages(){
        return this.listOfMessages;
    }

    public void getChatMessage(ChatForm chatForm) {
        ChatMessage chatMessage = new ChatMessage();
        if(chatForm.getMessageType().equalsIgnoreCase("Shout")) {
            chatMessage.setUserName(chatForm.getUserName());
            chatMessage.setUserMessage(chatForm.getUserMessage().toUpperCase());
        } else if(chatForm.getMessageType().equalsIgnoreCase("Whisper")){
            chatMessage.setUserName(chatForm.getUserName());
            chatMessage.setUserMessage(chatForm.getUserMessage().toLowerCase());
        } else {
            chatMessage.setUserName(chatForm.getUserName());
            chatMessage.setUserMessage(chatForm.getUserMessage());
        }
        addMessage(chatMessage);
    }

}
