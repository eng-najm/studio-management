package com.studio.features.attendance.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.ComboItemsProvider;
import com.studio.core.FieldValidator;
import com.studio.core.constants.ValidationType;
import com.studio.core.model.ComboItem;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.attendance.model.AttendanceModel;

public class EditAttendancePage extends JPanel {
    AppFiled idField = new AppFiled();
    JComboBox<ComboItem<?>> employeeCombo = new JComboBox<>();
    AppFiled checkInField = new AppFiled();
    AppFiled checkOutField = new AppFiled();
    AppFiled dateField = new AppFiled();
    JComboBox<String> statusCombo = new JComboBox<>(
            new String[] { "0 - Present", "1 - Absent", "2 - Late", "3 - Excused", "4 - Leave" });

    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditAttendancePage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addComboField(formPanel, "Employee", employeeCombo);
        addField(formPanel, "Check In (yyyy-mm-dd hh:mm:ss)", checkInField);
        addField(formPanel, "Check Out (yyyy-mm-dd hh:mm:ss)", checkOutField);
        addField(formPanel, "Date (yyyy-mm-dd)", dateField);
        addComboField(formPanel, "Status", statusCombo);

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

    public void populateEmployeeCombo() {
        ComboItemsProvider provider = new ComboItemsProvider();
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        for (ComboItem<Integer> item : provider.getEmployeeItems()) {
            model.addElement(item);
        }
        employeeCombo.setModel(model);
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

    public void setAttendanceData(AttendanceModel attendance) {
        idField.setText(String.valueOf(attendance.getId()));
        selectById(employeeCombo, attendance.getEmpId());
        checkInField.setText(String.valueOf(attendance.getCheckIn()));
        checkOutField.setText(String.valueOf(attendance.getCheckOut()));
        dateField.setText(String.valueOf(attendance.getDate()));
        statusCombo.setSelectedIndex(attendance.getStatus());
    }

    public AttendanceModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        int empId = getSelectedId(employeeCombo);
        Timestamp checkIn = checkInField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(checkInField.getText());
        Timestamp checkOut = checkOutField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(checkOutField.getText());
        Date date = dateField.getText().isEmpty() ? new Date()
                : java.sql.Date.valueOf(dateField.getText());
        int status = statusCombo.getSelectedIndex();
        return new AttendanceModel(id, empId, checkIn, checkOut, date, status);
    }

    private int getSelectedId(JComboBox<ComboItem<?>> combo) {
        ComboItem<?> item = (ComboItem<?>) combo.getSelectedItem();
        if (item != null && item.getValue() != null) {
            return (int) item.getValue();
        }
        return 0;
    }

    private void selectById(JComboBox<ComboItem<?>> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ComboItem<?> item = combo.getItemAt(i);
            if (item.getValue() != null && (int) item.getValue() == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    public List<String> validateFields() {
        List<String> errors = new ArrayList<>();
        addError(errors, FieldValidator.validateComboRequired("Employee", employeeCombo));
        addError(errors, FieldValidator.validate("Check In", checkInField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Check In", checkInField.getText(), ValidationType.TIMESTAMP));
        addError(errors, FieldValidator.validate("Check Out", checkOutField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Check Out", checkOutField.getText(), ValidationType.TIMESTAMP));
        addError(errors, FieldValidator.validate("Date", dateField.getText(), ValidationType.REQUIRED));
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
            checkInField.setText("");
            checkOutField.setText("");
            dateField.setText("");
            statusCombo.setSelectedIndex(0);
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
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

    public AppButton getDeleteButton() {
        return deleteButton;
    }
}
