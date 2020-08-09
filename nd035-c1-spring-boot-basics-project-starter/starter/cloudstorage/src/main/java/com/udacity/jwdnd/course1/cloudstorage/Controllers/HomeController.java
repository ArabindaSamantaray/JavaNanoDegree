package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    FileService fileService;
    UserMapper userMapper;
    NoteService noteService;

    public HomeController(FileService fileService, UserMapper userMapper, NoteService noteService) {
        this.fileService = fileService;
        this.userMapper = userMapper;
        this.noteService = noteService;
    }

    @GetMapping
    public ModelAndView getHome(Authentication authentication) throws Exception {
        String userName = authentication.getName();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Notes", noteService.getAllNotes(userName));
        modelAndView.addObject("Files", fileService.getListOfFiles(userName));
        return modelAndView;
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile fileUpload, Model model) throws Exception {
        fileService.saveFile(fileUpload);
        return "redirect:/home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) throws Exception {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer fileId){
        Files file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContenttype())).
            header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+file.getFilename()+"\"").body(
                new ByteArrayResource(file.getFiledata())
        );
    }
}
