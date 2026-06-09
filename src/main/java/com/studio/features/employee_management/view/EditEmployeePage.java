package com.studio.features.employee_management.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;

import com.studio.core.FieldValidator;
import com.studio.core.constants.ValidationType;
import com.studio.core.model.ComboItem;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.employee_management.model.EmployeeModel;

public class EditEmployeePage extends JPanel {
    AppFiled idField = new AppFiled();
    AppFiled firstNameField = new AppFiled();
    AppFiled middleNameField = new AppFiled();
    AppFiled lastNameField = new AppFiled();
    AppFiled sexField = new AppFiled();
    AppFiled phoneField = new AppFiled();
    AppFiled addressField = new AppFiled();
    JComboBox<ComboItem<?>> empTypeCombo = new JComboBox<>();
    AppFiled salaryField = new AppFiled();
    AppFiled userNameField = new AppFiled();
    AppFiled passwordField = new AppFiled();
    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;

    private AppButton backButton;

    public EditEmployeePage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addField(formPanel, "First Name", firstNameField);
        addField(formPanel, "Middle Name", middleNameField);
        addField(formPanel, "Last Name", lastNameField);
        addField(formPanel, "Sex", sexField);
        addField(formPanel, "Phone", phoneField);
        addField(formPanel, "Address", addressField);
        addComboField(formPanel, "Emp Type", empTypeCombo);
        addField(formPanel, "Salary", salaryField);
        addField(formPanel, "Username", userNameField);
        addField(formPanel, "Password", passwordField);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        buttonPanel.add(addButton);
        buttonPanel.add(applyChangeButton);
        buttonPanel.add(deleteButton);
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

    private void addComboField(JPanel panel, String labelText, JComboBox<?> combo) {
        AppLable label = new AppLable(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        combo.setPreferredSize(new Dimension(400, 45));
        combo.setMinimumSize(new Dimension(400, 45));
        combo.setMaximumSize(new Dimension(400, 45));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(combo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void setEmpTypeComboItems(List<ComboItem<String>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        model.addAll(items);
        empTypeCombo.setModel(model);
    }

    public void setEmployeeData(EmployeeModel employee) {
        idField.setText(employee.getId() + "");
        firstNameField.setText(employee.getFirstName());
        middleNameField.setText(employee.getMeddilName());
        lastNameField.setText(employee.getLastName());
        sexField.setText(String.valueOf(employee.getSex()));
        phoneField.setText(employee.getPhone());
        addressField.setText(employee.getAddress());
        selectByValue(empTypeCombo, employee.getEmpType());
        salaryField.setText(String.valueOf(employee.getSalary()));
        userNameField.setText(employee.getUserName());
        passwordField.setText(employee.getUserPassword());
    }

    private void selectByValue(JComboBox<ComboItem<?>> combo, String value) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ComboItem<?> item = combo.getItemAt(i);
            if (item.getValue() != null && item.getValue().equals(value)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    public EmployeeModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        char sex = sexField.getText().isEmpty() ? 'M' : sexField.getText().charAt(0);
        String phone = phoneField.getText();
        String address = addressField.getText();
        Date hireDate = new Date();
        String personType = "EMPLOYEE";
        int salary = salaryField.getText().isEmpty() ? 0 : Integer.parseInt(salaryField.getText());
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String empType = getSelectedValue(empTypeCombo);

        return new EmployeeModel(id, firstName, middleName, lastName, sex, phone, address, hireDate, personType, salary,
                userName, password, empType);
    }

    private String getSelectedValue(JComboBox<ComboItem<?>> combo) {
        ComboItem<?> item = (ComboItem<?>) combo.getSelectedItem();
        if (item != null && item.getValue() != null) {
            return (String) item.getValue();
        }
        return "";
    }

    public AppButton getApplyChangeButton() {
        return applyChangeButton;
    }

    public AppButton getBackButton() {
        return backButton;
    }

    public AppButton getAddButton() {
        return addButton;
    }

    public void setAddButton(AppButton addButton) {
        this.addButton = addButton;
    }

    public List<String> validateFields() {
        List<String> errors = new ArrayList<>();
        addError(errors, FieldValidator.validate("First Name", firstNameField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Middle Name", middleNameField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Last Name", lastNameField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Sex", sexField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Sex", sexField.getText(), ValidationType.SINGLE_CHAR));
        addError(errors, FieldValidator.validate("Phone", phoneField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Address", addressField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validateComboRequired("Emp Type", empTypeCombo));
        addError(errors, FieldValidator.validate("Salary", salaryField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Salary", salaryField.getText(), ValidationType.INTEGER));
        addError(errors, FieldValidator.validate("Username", userNameField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Password", passwordField.getText(), ValidationType.REQUIRED));
        return errors;
    }

    private void addError(List<String> errors, String error) {
        if (error != null) {
            errors.add(error);
        }
    }

    public void setAdd(boolean isAdd) {
        if (isAdd) {
            idField.setText("");
            firstNameField.setText("");
            middleNameField.setText("");
            lastNameField.setText("");
            sexField.setText("");
            phoneField.setText("");
            addressField.setText("");
            salaryField.setText("");
            userNameField.setText("");
            passwordField.setText("");
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
    }

}
