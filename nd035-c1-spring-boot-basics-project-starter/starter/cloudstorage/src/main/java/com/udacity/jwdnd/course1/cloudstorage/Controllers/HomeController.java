package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
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

@Controller
@RequestMapping("/home")
public class HomeController {

    FileService fileService;
    UserMapper userMapper;

    public HomeController(FileService fileService, UserMapper userMapper) {
        this.fileService = fileService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String getHome(){
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile fileUpload, Model model) throws Exception {
        fileService.saveFile(fileUpload);
        model.addAttribute("Files", fileService.getListOfFiles());
        return "home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) throws Exception {
        fileService.deleteFile(fileId);
        model.addAttribute("Files", fileService.getListOfFiles());
        return "home";
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
