package com.arabinda.persistenceDemo.Controller;

import com.arabinda.persistenceDemo.DTO.PlantDTO;
import com.arabinda.persistenceDemo.Entities.inventory.Plant;
import com.arabinda.persistenceDemo.Entities.inventory.Views;
import com.arabinda.persistenceDemo.Service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/dto")
    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);
        return new PlantDTO(plant.getName(), plant.getPrice());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/jsonView")
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.checkIfAPlantIsDelivered(id);
    }

    @GetMapping("/under-price/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> plantsCheaperThan(@PathVariable
        BigDecimal price) {
        return plantService.getCheapPlants(price);
    }
}
