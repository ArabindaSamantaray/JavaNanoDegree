package com.arabinda.persistenceDemo.Repository;

import com.arabinda.persistenceDemo.Entities.delivery.Delivery;

public interface DeliveryRepository {

    void persist(Delivery delivery);

    Delivery find(Long id);

    Delivery merge(Delivery delivery);

    void delete(Long id);
}
