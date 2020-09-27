package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    UserRepository userRepository;

    @Mock
    OrderRepository orderRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void submit() {
        Item item =new Item();
        item.setId(1l);
        item.setName("Mangoes");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart cart = new Cart();
        cart.setId(1l);
        cart.setItems(itemList);

        User user = new User();
        user.setUsername("Arabinda");
        user.setCart(cart);

        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
        Assert.assertEquals(orderController.submit("Arabinda").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getOrdersForUser() {
        Mockito.when( userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
        Assert.assertEquals(orderController.getOrdersForUser("Arabinda").getStatusCode(), HttpStatus.NOT_FOUND);


    }
}