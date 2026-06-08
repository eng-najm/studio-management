package com.studio.features.customer.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.studio.module.Person;

public class CustomerModel extends Person {

    public CustomerModel(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate, String personType) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate, personType);
    }

    public static CustomerModel fromResult(ResultSet resultSet) throws SQLException {
        return new CustomerModel(
                resultSet.getInt("ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("MIDDLE_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("SEX").charAt(0),
                resultSet.getString("PHONE"),
                resultSet.getString("ADDRESS"),
                new Date(),
                resultSet.getString("PERSON_TYPE"));
    }

}
