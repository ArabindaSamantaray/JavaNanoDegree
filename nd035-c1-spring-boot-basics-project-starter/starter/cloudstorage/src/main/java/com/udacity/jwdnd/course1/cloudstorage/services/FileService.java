package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.Utils.FileServiceUtils;
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
    FileServiceUtils fileServiceUtils;

    public FileService(UserMapper userMapper, FileMapper fileMapper, FileServiceUtils fileServiceUtils) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
        this.fileServiceUtils = fileServiceUtils;
    }

    public int saveFile(MultipartFile file) throws Exception {
        try {
            Integer userId = userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid();
            fileServiceUtils.validate(file, userId);
            return fileMapper.insertFiles(new Files(null, file.getOriginalFilename(), file.getContentType(), file.getSize(),  userId, file.getBytes()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Files> getListOfFiles(String userName) throws Exception {
        try{
            Integer userId = userMapper.getUser(userName).getUserid();
            return fileMapper.getAllFiles(userId);
        } catch (Exception e){
            throw new Exception("There was an error in getting the list of files");
        }

    }

    public void deleteFile(Integer fileId) {
        Integer userId = userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid();
        fileMapper.deleteFile(fileId, userId);
    }

    public Files getFile(Integer fileId) {
        Integer userId = userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid();
        return fileMapper.getFile(fileId, userId);
    }
}
