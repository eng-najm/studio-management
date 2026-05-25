package com.studio.module;

import java.util.Date;

enum Role {
    MANAGER, PHOTOGRAPHER, RECEPTIONIST
}

public class Employee extends Person {
    private int salary;
    private Role role;

    public Employee(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate, int salary, Role role) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate);
        this.role = role;
        this.salary = salary;

    }

    public Employee(String firstName, String meddilName, String lastName, char sex, String phone, String address,
            int salary, Role role) {
        super(firstName, meddilName, lastName, sex, phone, address);
        this.salary = salary;
        this.role = role;

    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
