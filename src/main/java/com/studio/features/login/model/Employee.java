package com.studio.features.login.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.studio.module.Person;

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

    public static Employee fromResult(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("PERSON_ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("MIDDLE_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("SEX").charAt(0),
                resultSet.getString("PHONE"),
                resultSet.getString("ADDRESS"),
                resultSet.getDate("HIRE_DATE"),
                resultSet.getInt("SALARY"),
                Role.valueOf(resultSet.getString("ROLE")),
                resultSet.getString("USER_NAME"),
                resultSet.getString("USER_PASSWORD"));
    }

}
