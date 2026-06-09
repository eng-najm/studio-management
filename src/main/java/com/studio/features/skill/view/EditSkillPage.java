package com.studio.features.skill.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;
import com.studio.core.FieldValidator;
import com.studio.core.constants.ValidationType;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.skill.model.SkillModel;

public class EditSkillPage extends JPanel {
    AppFiled idField = new AppFiled();
    AppFiled nameField = new AppFiled();
    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditSkillPage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addField(formPanel, "Name", nameField);

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

    public void setSkillData(SkillModel skill) {
        idField.setText(String.valueOf(skill.getId()));
        nameField.setText(skill.getName());
    }

    public SkillModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        String name = nameField.getText();
        return new SkillModel(id, name);
    }

    public List<String> validateFields() {
        List<String> errors = new ArrayList<>();
        addError(errors, FieldValidator.validate("Name", nameField.getText(), ValidationType.REQUIRED));
        return errors;
    }

    private void addError(List<String> errors, String error) {
        if (error != null) {
            errors.add(error);
        }
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

    public void setAdd(boolean isAdd) {
        if (isAdd) {
            idField.setText("");
            nameField.setText("");
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
    }
}
