package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetDTO savePet(Pet pet, Long ownerId){
        Customer customer = customerRepository.findById(ownerId).get();
        pet.setCustomer(customer);
        Pet savedPet = petRepository.saveAndFlush(pet);
        customer.addPets(pet);
        customerRepository.saveAndFlush(customer);
        return convertPetToDTO(savedPet);
    }

    public PetDTO getPetById(Long id){
        Pet pet = petRepository.findById(id).get();
        return convertPetToDTO(pet);
    }

    public List<PetDTO> getPetsByOwner(Long ownerId){
        Customer customer = customerRepository.findById(ownerId).get();
        List<Pet> petList = customer.getPetList();
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet: petList) {
            petDTOList.add(convertPetToDTO(pet));
        }
        return petDTOList;
    }

    private PetDTO convertPetToDTO(Pet savedPet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(savedPet.getCustomer().getId());
        petDTO.setName(savedPet.getName());
        petDTO.setType(savedPet.getType());
        petDTO.setBirthDate(savedPet.getBirthDate());
        petDTO.setId(savedPet.getId());
        petDTO.setNotes(savedPet.getNotes());
        return petDTO;
    }

    public List<PetDTO> getAllPets() {
        List<Pet> petList = petRepository.findAll();
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet: petList) {
            petDTOList.add(convertPetToDTO(pet));
        }
        return petDTOList;
    }
}
