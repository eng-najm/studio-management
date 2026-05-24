package com.studio.module;

import java.util.Date;

public class Customer extends Person {

    public Customer(String firstName, String meddilName, String lastName, char sex, String phone, String address) {
        super(firstName, meddilName, lastName, sex, phone, address);
    }

    public Customer(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate);
    }

}
