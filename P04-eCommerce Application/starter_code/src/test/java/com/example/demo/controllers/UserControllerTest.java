package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("Arabinda");
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1l)).thenReturn(userOptional);
        ResponseEntity<User> byId = userController.findById(1l);
        Assert.assertEquals(byId.getBody().getUsername(), "Arabinda");
    }

    @Test
    public void findByUserName() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("Arabinda");
        Mockito.when(userRepository.findByUsername("Arabinda")).thenReturn(user);
        ResponseEntity<User> arabinda = userController.findByUserName("Arabinda");
        Assert.assertEquals(arabinda.getBody().getUsername(), "Arabinda");
    }

    @Test
    public void createUserFail() {
        CreateUserRequest user = new CreateUserRequest();
        user.setPassword("pass");
        user.setUsername("Arabinda");
        user.setConfirmPassword("pass");
        ResponseEntity<User> user1 = userController.createUser(user);
        Assert.assertEquals(user1.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createUserPass() {
        CreateUserRequest user = new CreateUserRequest();
        user.setPassword("passWORD");
        user.setUsername("Arabinda");
        user.setConfirmPassword("passWORD");
        Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("hashedPassword");
        ResponseEntity<User> user1 = userController.createUser(user);
        Assert.assertEquals(user1.getStatusCode(), HttpStatus.OK);
    }
}