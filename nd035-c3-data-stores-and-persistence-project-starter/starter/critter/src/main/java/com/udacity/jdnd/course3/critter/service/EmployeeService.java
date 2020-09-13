package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return convertEmployeeToDTO(savedEmployee);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> allEmployees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(Employee employee: allEmployees){
            employeeDTOList.add(convertEmployeeToDTO(employee));
        }
        return employeeDTOList;
    }

    public EmployeeDTO getEmployee(Long id){
        return convertEmployeeToDTO((Employee)employeeRepository.findById(id).get());
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId){
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setScheduleList(daysAvailable);
        employeeRepository.saveAndFlush(employee);
    }

    public List<EmployeeDTO> findEmployeeForService(EmployeeRequestDTO employeeRequestDTO){
        DayOfWeek dayOfWeek = employeeRequestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        List<Employee> employeesOnGivenDate = employeeRepository.getEmployeesOnGivenDate(dayOfWeek);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee: employeesOnGivenDate){
            if(employee.getListOfSkills().containsAll(skills)){
                employeeDTOList.add(convertEmployeeToDTO(employee));
            }
        }
        return employeeDTOList;
    }
    private EmployeeDTO convertEmployeeToDTO(Employee savedEmployee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(savedEmployee.getId());
        employeeDTO.setDaysAvailable(savedEmployee.getScheduleList());
        employeeDTO.setSkills(savedEmployee.getListOfSkills());
        employeeDTO.setName(savedEmployee.getName());
        return employeeDTO;
    }
}
