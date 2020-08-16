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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/createCredentials")
    public String getCredentials(@ModelAttribute("Credentials")Credentials credentials, Authentication authentication, RedirectAttributes redirectAttributes){
        String userName = authentication.getName();
        if(credentials.getCredentialid()==null){
            try{
                credentialService.createCredentials(credentials, userName);
                redirectAttributes.addFlashAttribute("successMessage", "The credentials were correctly stored in the database.");
                return "redirect:/result";
            } catch (Exception e){
                redirectAttributes.addFlashAttribute("failureMessage", "The credentials were not correctly stored in the database.");
                return "redirect:/result";
            }
        } else {
            try{
                credentialService.updateCredentials(credentials, userName);
                redirectAttributes.addFlashAttribute("successMessage", "The credentials were correctly updated in the database. ");
                return "redirect:/result";
            } catch (Exception e){
                redirectAttributes.addFlashAttribute("failureMessage", "The credentials were not correctly updated in the database. ");
                return "redirect:/result";
            }

        }
    }

    @GetMapping("/deleteCredentials/{credentialId}")
    public String deleteCredentials(@PathVariable("credentialId") Integer credentialId, Authentication authentication, RedirectAttributes redirectAttributes){
        try{
            String userName = authentication.getName();
            credentialService.deleteCredentials(credentialId, userName);
            redirectAttributes.addFlashAttribute("successMessage", "The credentials were correctly deleted from the database.");
            return "redirect:/result";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("failureMessage", "The credentials were not deleted from the database. ");
            return "redirect:/result";
        }
    }
}
