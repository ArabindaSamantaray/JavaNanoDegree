package com.arabinda.persistenceDemo.Entities.delivery;

import com.arabinda.persistenceDemo.Entities.inventory.Plant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@NamedQueries(
    {
        @NamedQuery(
            name = "Delivery.findByName",
            query = "select d from Delivery d where d.recipientName = :recipientName"
        )
    }
)
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nationalized
    private String recipientName;

    @Column(name="address_full", length = 500)
    private String address;

    private LocalDateTime localDateTime;

    @Type(type = "yes_no")
    private boolean isDelivered;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Plant> plantList;



    public Delivery() {
    }

    public Delivery(String recipientName, String address, LocalDateTime localDateTime, boolean isDelivered) {
        this.recipientName = recipientName;
        this.address = address;
        this.localDateTime = localDateTime;
        this.isDelivered = isDelivered;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Delivery{" + "recipientName='" + recipientName + '\'' + ", address='" + address + '\''
            + ", localDateTime=" + localDateTime + ", isDelivered=" + isDelivered + ", plantList=" + plantList + '}';
    }
}
