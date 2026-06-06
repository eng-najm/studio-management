package com.studio.features.employee_management.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.employee_management.model.EmployeeModel;

public class EditEmployeePage extends JPanel {
    AppFiled idField = new AppFiled();
    AppFiled firstNameField = new AppFiled();
    AppFiled middleNameField = new AppFiled();
    AppFiled lastNameField = new AppFiled();
    AppFiled phoneField = new AppFiled();
    AppFiled addressField = new AppFiled();
    AppFiled salaryField = new AppFiled();
    AppFiled userNameField = new AppFiled();
    AppFiled passwordField = new AppFiled();
    private EmployeeModel currentData;
    private AppButton applyChangeButton;

    private AppButton backButton;

    public EditEmployeePage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addField(formPanel, "First Name", firstNameField);
        addField(formPanel, "Middle Name", middleNameField);
        addField(formPanel, "Last Name", lastNameField);
        addField(formPanel, "Phone", phoneField);
        addField(formPanel, "Address", addressField);
        addField(formPanel, "Salary", salaryField);
        addField(formPanel, "Username", userNameField);
        addField(formPanel, "Password", passwordField);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(applyChangeButton);
        buttonPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String labelText, AppFiled field) {
        AppLable label = new AppLable(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void setEmployeeData(EmployeeModel employee) {
        this.currentData = employee;
        idField.setText(employee.getId() + "");
        firstNameField.setText(employee.getFirstName());
        middleNameField.setText(employee.getMeddilName());
        lastNameField.setText(employee.getLastName());
        phoneField.setText(employee.getPhone());
        addressField.setText(employee.getAddress());
        salaryField.setText(String.valueOf(employee.getSalary()));
        userNameField.setText(employee.getUserName());
        passwordField.setText(employee.getUserPassword());
    }

    public EmployeeModel getCurrentData() {
        currentData.setId(Integer.parseInt(idField.getText()));
        currentData.setId(Integer.parseInt(idField.getText()));
        currentData.setFirstName(firstNameField.getText());
        currentData.setMeddilName(middleNameField.getText());
        currentData.setLastName(lastNameField.getText());
        currentData.setPhone(phoneField.getText());
        currentData.setAddress(addressField.getText());
        currentData.setSalary(Integer.parseInt(salaryField.getText()));
        currentData.setUserName(userNameField.getText());
        currentData.setUserPassword(passwordField.getText());

        return currentData;
    }

    public AppButton getApplyChangeButton() {
        return applyChangeButton;
    }

    public AppButton getBackButton() {
        return backButton;
    }
}
