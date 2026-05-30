package com.studio.features.login.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.studio.core.constants.AppStrings;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.login.controller.LoginController;

public class LoginPage extends JPanel {
    public AppFiled usernameField = new AppFiled();
    public AppFiled passwordField = new AppFiled();
    public AppButton submitButton = new AppButton(AppStrings.LOGIN);

    public LoginPage() {
        new LoginController(this);
        this.setSize(500, 500);
        JPanel mainPnel = new JPanel();
        mainPnel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        mainPnel.setLayout(new BoxLayout(mainPnel, BoxLayout.Y_AXIS));

        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setMaximumSize(new Dimension(150, 40));
        AppLable userLabel = new AppLable(AppStrings.USER_NAME);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPnel.add(userLabel);
        mainPnel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPnel.add(usernameField);

        mainPnel.add(Box.createRigidArea(new Dimension(0, 15)));
        AppLable pasLable = new AppLable(AppStrings.USER_PASSWORD);
        pasLable.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPnel.add(pasLable);
        mainPnel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPnel.add(passwordField);

        mainPnel.add(Box.createRigidArea(new Dimension(0, 25)));
        mainPnel.add(submitButton);

        this.add(mainPnel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
