package com.studio.module;

import java.util.Date;

public abstract class Person {
    private int id;
    private String firstName;
    private String meddilName;
    private String lastName;
    private char sex;
    private String phone;
    private String address;
    private Date hireDate;
    private String personType;

    public Person(int id, String firstName, String meddilName, String lastName, char sex, String phone, String address,
            Date hireDate, String personType) {
        this.id = id;
        this.firstName = firstName;
        this.meddilName = meddilName;
        this.lastName = lastName;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.hireDate = hireDate;
        this.personType = personType;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMeddilName() {
        return meddilName;
    }

    public void setMeddilName(String meddilName) {
        this.meddilName = meddilName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
