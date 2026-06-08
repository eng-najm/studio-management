package com.studio.module;

import java.util.Date;

public class Customer extends Person {

    public Customer(int id, String firstName, String meddilName, String lastName, char sex, String phone,
            String address, Date hireDate, String personType) {
        super(id, firstName, meddilName, lastName, sex, phone, address, hireDate, personType);

    }

}
