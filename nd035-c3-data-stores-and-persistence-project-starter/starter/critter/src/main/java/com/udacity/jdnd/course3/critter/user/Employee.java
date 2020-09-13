package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    @ElementCollection
    Set<EmployeeSkill> listOfSkills;

    @ElementCollection
    Set<DayOfWeek> scheduleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getListOfSkills() {
        return listOfSkills;
    }

    public void setListOfSkills(Set<EmployeeSkill> listOfSkills) {
        this.listOfSkills = listOfSkills;
    }

    public Set<DayOfWeek> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(Set<DayOfWeek> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Long getId() {
        return Id;
    }
}
