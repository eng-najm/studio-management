package com.studio.module;

import java.util.Date;

enum Role {
    MANAGER, PHOTOGRAPHER, RECEPTIONIST, LASER_ENGRAVER, PRINT_OPERATOR
}

public class Employee extends Person {
    private int salary;
    private Role role;
    private String userName;
    private String userPassword;

    public Employee(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate, int salary, Role role, String userName, String userPassword) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate);
        this.salary = salary;
        this.role = role;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Employee(String firstName, String meddilName, String lastName, char sex, String phone, String address,
            int salary, Role role, String userName, String userPassword) {
        super(firstName, meddilName, lastName, sex, phone, address);
        this.salary = salary;
        this.role = role;
        this.userName = userName;
        this.userPassword = userPassword;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
