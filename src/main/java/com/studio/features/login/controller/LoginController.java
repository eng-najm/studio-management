package com.studio.features.login.controller;

import javax.swing.JOptionPane;

import com.studio.core.Either;
import com.studio.features.login.LoginDAO;
import com.studio.features.login.model.Employee;
import com.studio.features.login.view.LoginPage;

public class LoginController {
    private LoginPage loginPage;
    private LoginDAO loginDAO;

    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
        loginDAO = new LoginDAO();
        init();
    }

    public void init() {
        loginPage.submitButton.addActionListener(e -> login());
    }

    void login() {
        Either<Employee, Exception> result = loginDAO.getEmployee(loginPage.usernameField.getText(),
                loginPage.passwordField.getText());
        if (result.isLeft()) {
            JOptionPane.showMessageDialog(loginPage, "Yes", "Succes", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(loginPage, "no", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
