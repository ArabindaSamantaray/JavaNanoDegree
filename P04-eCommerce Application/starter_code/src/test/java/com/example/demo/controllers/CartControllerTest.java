package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

public class CartControllerTest {

    @InjectMocks
    CartController cartController;

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ItemRepository itemRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTocart() {
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1);
        modifyCartRequest.setUsername("Arabinda");
        modifyCartRequest.setQuantity(10);

        User user = new User();
        user.setUsername("Arabinda");

        Cart cart = new Cart();
        user.setCart(cart);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
        Item item = new Item();
        item.setName("Mangoes");
        item.setPrice(BigDecimal.TEN);
        item.setId(1l);
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(itemOptional);
        Assert.assertEquals(cartController.addTocart(modifyCartRequest).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void removeFromcart() {
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1);
        modifyCartRequest.setUsername("Arabinda");
        modifyCartRequest.setQuantity(10);

        User user = new User();
        user.setUsername("Arabinda");

        Cart cart = new Cart();
        user.setCart(cart);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
        Item item = new Item();
        item.setName("Mangoes");
        item.setPrice(BigDecimal.TEN);
        item.setId(1l);
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(itemOptional);
        Assert.assertEquals(cartController.removeFromcart(modifyCartRequest).getStatusCode(), HttpStatus.OK);

    }
}