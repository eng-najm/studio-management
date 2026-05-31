package com.studio.features.login.controller;

import javax.swing.JOptionPane;

import com.studio.Main;
import com.studio.core.Either;
import com.studio.core.constants.AppRoutes;
import com.studio.features.login.LoginDAO;
import com.studio.features.login.model.User;
import com.studio.features.login.view.LoginPage;

public class LoginController {
    private LoginPage loginPage;
    private LoginDAO loginDAO;
    private Main main;

    public LoginController(LoginPage loginPage, Main main) {
        this.loginPage = loginPage;
        loginDAO = new LoginDAO();
        this.main = main;
        init();
    }

    public void init() {
        loginPage.submitButton.addActionListener(e -> login());
    }

    void login() {
        Either<User, Exception> result = loginDAO.getEmployee(loginPage.usernameField.getText(),
                loginPage.passwordField.getText());
        if (result.isLeft()) {
            main.goTo(AppRoutes.DASHBOARD);
        } else {
            JOptionPane.showMessageDialog(loginPage, "no", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
