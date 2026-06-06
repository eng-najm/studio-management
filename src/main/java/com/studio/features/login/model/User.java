package com.studio.features.login.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String userName;
    private String userPassword;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public static User fromResult(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getString("USER_NAME"),
                resultSet.getString("USER_PASSWORD"));
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
