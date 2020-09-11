package com.arabinda.persistenceDemo.Repository;

import com.arabinda.persistenceDemo.Entities.inventory.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("select p.delivery.isDelivered from Plant p where p.id = :plantId")
    boolean isDelivered(@Param("plantId") Long plantId);

    @Query("select p from Plant p where p.price < :price")
    List<Plant> lessCostOptions(@Param("price") BigDecimal price);
}
