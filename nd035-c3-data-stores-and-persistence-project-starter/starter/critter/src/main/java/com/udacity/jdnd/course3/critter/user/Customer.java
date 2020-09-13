package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Pet> petList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPet() {
        return petList;
    }

    public void setPet(List<Pet> pet) {
        this.petList = pet;
    }

    public void addPets(Pet pet){
        if(petList ==null) {
            petList = new ArrayList<>();
            petList.add(pet);
        } else{
            this.petList.add(pet);
        }
    }

    public Long getId() {
        return Id;
    }

    public List<Pet> getPetList() {
        return petList;
    }
}
