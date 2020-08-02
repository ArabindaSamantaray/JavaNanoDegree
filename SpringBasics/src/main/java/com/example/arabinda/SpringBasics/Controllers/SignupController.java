package com.example.arabinda.SpringBasics.Controllers;

import com.example.arabinda.SpringBasics.Model.User;
import com.example.arabinda.SpringBasics.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUp() {
        return "signup";
    }

    @PostMapping
    public String createUser(@ModelAttribute("User")User user, Model model) {
        userService.createUser(user);
        return "login";
    }
}
