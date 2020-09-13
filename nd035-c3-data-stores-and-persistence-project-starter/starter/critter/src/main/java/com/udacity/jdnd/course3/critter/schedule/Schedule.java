package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToMany
    private List<Employee> employeeList;

    @ManyToMany
    private List<Pet> petList;

    private LocalDate scheduledDate;

    @ElementCollection
    private Set<EmployeeSkill> skillList;

    public Long getId() {
        return Id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public LocalDate getDate() {
        return scheduledDate;
    }

    public void setDate(LocalDate date) {
        this.scheduledDate = date;
    }

    public Set<EmployeeSkill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<EmployeeSkill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        return "Schedule{" + "Id=" + Id + ", employeeList=" + employeeList + ", petList=" + petList + ", date=" + scheduledDate
            + ", skillList=" + skillList + '}';
    }
}
