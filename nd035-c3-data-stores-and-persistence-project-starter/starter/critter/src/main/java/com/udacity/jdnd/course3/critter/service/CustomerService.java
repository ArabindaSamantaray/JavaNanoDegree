package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;


    public CustomerDTO saveCustomer(Customer customer){
        Customer customerSaved = customerRepository.saveAndFlush(customer);
        return createCustomerDto(customer);
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> allCustomers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer customer: allCustomers) {
            customerDTOList.add(createCustomerDto(customer));
        }
        return customerDTOList;
    }

    public CustomerDTO getOwnerByPetId(Long petId){
        Pet pet = petRepository.findById(petId).get();
        return createCustomerDto(pet.getCustomer());
    }


    public CustomerDTO createCustomerDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        List<Pet> petList = customer.getPetList();
        List<Long> petIdList = new ArrayList<>();
        if(petList!=null){
            for(Pet pet: petList){
                petIdList.add(pet.getId());
            }
        }
        customerDTO.setPetIds(petIdList);
        return customerDTO;
    }
}
