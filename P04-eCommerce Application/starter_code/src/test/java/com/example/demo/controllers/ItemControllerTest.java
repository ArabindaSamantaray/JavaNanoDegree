package com.example.demo.controllers;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ItemControllerTest {

    @InjectMocks
    ItemController itemController;

    @Mock
    ItemRepository itemRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItems() {
        Item item = new Item();
        item.setId(1l);
        item.setPrice(BigDecimal.TEN);
        item.setName("Mangoes");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        Mockito.when(itemRepository.findAll()).thenReturn(itemList);
        Assert.assertEquals(itemController.getItems().getBody().get(0).getName(), "Mangoes");
    }

    @Test
    public void getItemById() {
        Item item = new Item();
        item.setId(1l);
        item.setPrice(BigDecimal.TEN);
        item.setName("Mangoes");
        Optional<Item> optionalItem = Optional.of(item);
        Mockito.when(itemRepository.findById(1l)).thenReturn(optionalItem);
        Assert.assertEquals(itemController.getItemById(1l).getBody().getName(),"Mangoes" );
    }

    @Test
    public void getItemsByName() {
        Mockito.when(itemRepository.findByName(Mockito.anyString())).thenReturn(null);
        Assert.assertEquals(itemController.getItemsByName("Mangoes").getStatusCode(), HttpStatus.NOT_FOUND);
    }
}