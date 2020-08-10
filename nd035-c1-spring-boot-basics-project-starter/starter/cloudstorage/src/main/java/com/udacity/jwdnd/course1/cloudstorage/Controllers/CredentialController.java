package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/createCredentials")
    public String getCredentials(@ModelAttribute("Credentials")Credentials credentials, Authentication authentication){
        String userName = authentication.getName();
        if(credentials.getCredentialid()==null){
            credentialService.createCredentials(credentials, userName);
        } else {
            credentialService.updateCredentials(credentials, userName);
        }

        return "redirect:/home";
    }

    @GetMapping("/deleteCredentials/{credentialId}")
    public String deleteCredentials(@PathVariable("credentialId") Integer credentialId, Authentication authentication){
        String userName = authentication.getName();
        credentialService.deleteCredentials(credentialId, userName);
        return "redirect:/home";
    }
}
