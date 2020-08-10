package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.Utils.UserServiceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private HashService hashService;
    private UserMapper userMapper;
    private UserServiceUtils userServiceUtils;

    public UserService(HashService hashService, UserMapper userMapper, UserServiceUtils userServiceUtils) {
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.userServiceUtils = userServiceUtils;
    }

    public int createUser(Users user) throws Exception {
        userServiceUtils.validateUser(user);
        try{
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

            int records = userMapper.insertUser(new Users(null, user.getUsername(),encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
            return records;
        } catch (Exception e){
            throw new Exception("There was an error in creating the User. Kindly try again");
        }

    }

    public Users getUser() throws Exception {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            return userMapper.getUser(userName);
        } catch (Exception e){
            throw new Exception("The user '" + userName + "' could not be found in the database");
        }

    }
}
