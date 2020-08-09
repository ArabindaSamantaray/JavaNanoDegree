package com.udacity.jwdnd.course1.cloudstorage.services.Utils;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Users;
import org.springframework.stereotype.Component;

@Component
public class UserServiceUtils {
    UserMapper userMapper;

    public UserServiceUtils(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void  validateUser(Users user) throws Exception {
        if(userMapper.getUser(user.getUsername())!=null){
            throw new Exception("The username '" + user.getUsername() +"' is already in use. Kindly choose another");
        }
    }
}
