package com.arabinda.persistenceDemo.Controller;

import com.arabinda.persistenceDemo.DTO.RecipientAndPrice;
import com.arabinda.persistenceDemo.Entities.delivery.Delivery;
import com.arabinda.persistenceDemo.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody
        Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable
        Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }

    @GetMapping("/byName/{name}")
    public List<Delivery> getListByName(@PathVariable String name){
        return deliveryService.getByName(name);
    }
}