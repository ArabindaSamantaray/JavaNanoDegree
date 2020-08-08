package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    UserMapper userMapper;
    FileMapper fileMapper;

    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public int saveFile(MultipartFile file) throws Exception {

        Integer userId = userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid();
        try {
            return fileMapper.insertFiles(new Files(null, file.getOriginalFilename(), file.getContentType(), file.getSize(),  userId, file.getBytes()));
        } catch (Exception e) {
            throw new Exception("There was an error in uploading file to database", e);
        }
    }

    public List<Files> getListOfFiles() {
        Integer userId = userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid();
        return fileMapper.getAllFiles(userId);
    }
}
