package com.udacity.jwdnd.course1.cloudstorage.services.Utils;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Component
public class FileServiceUtils {

    FileMapper fileMapper;

    public FileServiceUtils(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void validate(MultipartFile file, Integer userId) throws Exception {
        List<Files> listOfFiles= fileMapper.getAllFiles(userId);
        if(listOfFiles!= null) {
            for (Files oldFiles:listOfFiles) {
                if(file.getOriginalFilename().equalsIgnoreCase(oldFiles.getFilename())){
                    throw new Exception("You cannot insert a file with the same name as a file previously stored");
                }
            }
        }
        if(file.getSize()==0){
            throw new Exception("The size of file being uploaded is 0. Kindly upload a valid file");
        }
    }

}
