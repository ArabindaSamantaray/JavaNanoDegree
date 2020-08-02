package com.example.arabinda.SpringBasics.Model;

public class ChatForm {

    private String userMessage;
    private String messageType;


    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @Override
    public String toString() {
        return "ChatForm{" + "userMessage='" + userMessage + '\'' + ", messageType='" + messageType + '\'' + '}';
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
