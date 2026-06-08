package com.studio.features.employee_management.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.studio.module.Person;

public class EmployeeModel extends Person {

    private int salary;
    private String userName;
    private String userPassword;
    private String emp_type;

    public EmployeeModel(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate, String personType, int salary, String userName,
            String userPassword, String emp_type) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate, personType);
        this.salary = salary;
        this.userName = userName;
        this.userPassword = userPassword;
        this.emp_type = emp_type;
    }

    public String getEmpType() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public static EmployeeModel fromResult(ResultSet resultSet) throws SQLException {
        return new EmployeeModel(
                resultSet.getInt("PERSON_ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("MIDDLE_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("SEX").charAt(0),
                resultSet.getString("PHONE"),
                resultSet.getString("ADDRESS"),
                resultSet.getDate("HIRE_DATE"),
                resultSet.getString("PERSON_TYPE"),
                resultSet.getInt("SALARY"),
                resultSet.getString("USER_NAME"),
                resultSet.getString("USER_PASSWORD"),
                resultSet.getString("EMP_TYPE"));
    }

}
