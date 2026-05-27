package com.studio.features.login.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.studio.core.AppStrings;
import com.studio.core.shared_widgets.CustomButton;
import com.studio.core.shared_widgets.CustomFiled;
import com.studio.core.shared_widgets.CustomLable;

public class LoginPage extends JFrame {
    private CustomFiled usernameField = new CustomFiled();
    private CustomFiled passwordField = new CustomFiled();
    private CustomButton submitButton = new CustomButton(AppStrings.login);

    LoginPage() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel mainPnel = new JPanel();
        mainPnel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        mainPnel.setLayout(new BoxLayout(mainPnel, BoxLayout.Y_AXIS));

        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setMaximumSize(new Dimension(150, 40));
        CustomLable userLabel = new CustomLable(AppStrings.username);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPnel.add(userLabel);
        mainPnel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPnel.add(usernameField);

        mainPnel.add(Box.createRigidArea(new Dimension(0, 15)));
        CustomLable pasLable = new CustomLable(AppStrings.password);
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
