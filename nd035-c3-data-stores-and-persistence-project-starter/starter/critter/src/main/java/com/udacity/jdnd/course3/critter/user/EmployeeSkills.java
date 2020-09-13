package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeSkills {

    private String skills;

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
