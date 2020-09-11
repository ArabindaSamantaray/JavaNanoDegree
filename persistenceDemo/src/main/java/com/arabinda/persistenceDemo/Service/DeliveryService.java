package com.arabinda.persistenceDemo.Service;

import com.arabinda.persistenceDemo.DTO.RecipientAndPrice;
import com.arabinda.persistenceDemo.Entities.delivery.Delivery;
import com.arabinda.persistenceDemo.Entities.inventory.Plant;
import com.arabinda.persistenceDemo.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    EntityManager entityManager;

    public Long save(Delivery delivery) {
        delivery.getPlantList().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public List<Delivery> getByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        query.setParameter("recipientName", name);
        return query.getResultList();
        //return entityManager.createNamedQuery("Delivery.findByName", Delivery.class).setParameter("recipientName", name).getResultList();
    }

    /**
     * Using Criteria Builder was complicated, need to work on that more
     */
    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        query.select(
            cb.construct(
                RecipientAndPrice.class,
                root.get("delivery").get("recipientName"),
                cb.sum(root.get("price"))))
            .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}