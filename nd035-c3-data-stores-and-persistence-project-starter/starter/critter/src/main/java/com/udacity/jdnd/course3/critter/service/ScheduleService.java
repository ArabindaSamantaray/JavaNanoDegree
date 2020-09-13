package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;
    
    

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployeeList(employeeRepository.findAllById(scheduleDTO.getEmployeeIds()));
        schedule.setPetList(petRepository.findAllById(scheduleDTO.getPetIds()));
        schedule.setSkillList(scheduleDTO.getActivities());
        Schedule savedSchedule = scheduleRepository.saveAndFlush(schedule);
        return convertScheduleToScheduleDTO(savedSchedule);
    }

    public List<ScheduleDTO> getSchedule(){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule: scheduleList){
            scheduleDTOList.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedule.getSkillList());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setEmployeeIds(schedule.getEmployeeList().stream().map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPetList().stream().map(Pet::getId).collect(Collectors.toList()));
        return scheduleDTO;
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        List<Schedule> scheduleList = scheduleRepository.getScheduleForEmployee(employee);
        List<ScheduleDTO>scheduleDTOList = new ArrayList<>();
        for (Schedule schedule: scheduleList){
            scheduleDTOList.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    public List<ScheduleDTO> getScheduleForPet(long petId){
        Pet pet = petRepository.findById(petId).get();
        List<Schedule> scheduleForPet = scheduleRepository.getScheduleForPet(pet);
        List<ScheduleDTO>scheduleDTOList = new ArrayList<>();
        for (Schedule schedule: scheduleForPet){
            scheduleDTOList.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        List<Pet> petList = customerRepository.findById(customerId).get().getPetList();
        List<Schedule> scheduleList = new ArrayList<>();
        for(Pet pet: petList){
            scheduleList.addAll(scheduleRepository.getScheduleForPet(pet));
        }
        List<ScheduleDTO>scheduleDTOList = new ArrayList<>();
        for (Schedule schedule: scheduleList){
            scheduleDTOList.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }
}
