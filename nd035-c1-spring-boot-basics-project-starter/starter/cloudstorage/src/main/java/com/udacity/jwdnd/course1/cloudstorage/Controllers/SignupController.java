package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("User") Users user, Model model){
        try{
            userService.createUser(user);
            model.addAttribute("signupSuccess", true);
        } catch (Exception e) {
            model.addAttribute("signupError", e.getMessage());
        }

        return "signup";
    }
}
