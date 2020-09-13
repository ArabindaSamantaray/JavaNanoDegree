package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where :employee MEMBER of s.employeeList")
    List<Schedule> getScheduleForEmployee(@Param("employee")Employee employee);

    @Query("select s from Schedule s where :pet MEMBER of s.petList")
    List<Schedule> getScheduleForPet(@Param("pet") Pet pet);


}
