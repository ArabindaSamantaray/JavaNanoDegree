package com.arabinda.persistenceDemo.Service;

import com.arabinda.persistenceDemo.Entities.inventory.Plant;
import com.arabinda.persistenceDemo.Repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;


    public Plant getPlantByName(String name){
        return new Plant();
    }

    public Long savePlant(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public boolean checkIfAPlantIsDelivered(Long id){
        return plantRepository.isDelivered(id);
    }

    public List<Plant> getCheapPlants(BigDecimal price){
        return plantRepository.lessCostOptions(price);
    }
}
