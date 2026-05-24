package com.studio.module;

import java.util.ArrayList;

public class Skill {
    private int id;
    private String skillName;
    private ArrayList<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Skill(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

}
