package com.studio.features.customer.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.customer.model.CustomerModel;

public class EditCustomerPage extends JPanel {
    AppFiled idField = new AppFiled();
    AppFiled firstNameField = new AppFiled();
    AppFiled middleNameField = new AppFiled();
    AppFiled lastNameField = new AppFiled();
    AppFiled sexField = new AppFiled();
    AppFiled phoneField = new AppFiled();
    AppFiled addressField = new AppFiled();
    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditCustomerPage() {
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

    public void setCustomerData(CustomerModel customer) {
        idField.setText(customer.getId() + "");
        firstNameField.setText(customer.getFirstName());
        middleNameField.setText(customer.getMeddilName());
        lastNameField.setText(customer.getLastName());
        sexField.setText(String.valueOf(customer.getSex()));
        phoneField.setText(customer.getPhone());
        addressField.setText(customer.getAddress());
    }

    public CustomerModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        char sex = sexField.getText().isEmpty() ? 'M' : sexField.getText().charAt(0);
        String phone = phoneField.getText();
        String address = addressField.getText();
        Date hireDate = new Date();
        String personType = "CUSTOMER";

        return new CustomerModel(id, firstName, middleName, lastName, sex, phone, address, hireDate, personType);
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

    public void setAdd(boolean isAdd) {
        if (isAdd) {
            idField.setText("");
            firstNameField.setText("");
            middleNameField.setText("");
            lastNameField.setText("");
            sexField.setText("");
            phoneField.setText("");
            addressField.setText("");
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
    }
}
