package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Models.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {
    NoteService noteService;
    NoteMapper noteMapper;

    public NotesController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @PostMapping("/createNote")
    public String createNotes(@ModelAttribute("Notes") Notes notes, Model model, Authentication authentication){
        if(notes.getNoteid()==null){
            noteService.createNotes(notes, authentication.getName());
        } else {
            noteService.updateNotes(notes, authentication.getName());
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNotes(@PathVariable("noteId") Integer noteId, Model model, Authentication authentication){
        noteService.deleteNotes(noteId, authentication.getName());
        return "redirect:/home";
    }
}
