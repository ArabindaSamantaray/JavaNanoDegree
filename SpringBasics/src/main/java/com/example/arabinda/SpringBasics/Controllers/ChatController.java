package com.example.arabinda.SpringBasics.Controllers;

import com.example.arabinda.SpringBasics.Model.ChatForm;
import com.example.arabinda.SpringBasics.Model.ChatMessage;
import com.example.arabinda.SpringBasics.Service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public String addMessage(@ModelAttribute("ChatForm") ChatForm chatForm, Model model) {
        messageService.getChatMessage(chatForm);
        model.addAttribute("ChatMessage", messageService.getListOfMessages());
        return "chat";
    }

    @GetMapping
    public String getListOfMessages(@ModelAttribute("ChatForm") ChatForm chatForm, Model model){
    model.addAttribute("ChatMessage", messageService.getListOfMessages());
    return "chat";
    }
}
