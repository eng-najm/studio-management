package com.studio.features.coupon.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.coupon.model.CouponModel;

public class EditCouponPage extends JPanel {
    AppFiled idField = new AppFiled();
    AppFiled codeField = new AppFiled();
    AppFiled discountPercentField = new AppFiled();
    AppFiled statusField = new AppFiled();
    AppFiled noteField = new AppFiled();
    AppFiled startAtField = new AppFiled();
    AppFiled endAtField = new AppFiled();
    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditCouponPage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addField(formPanel, "Code", codeField);
        addField(formPanel, "Discount Percent", discountPercentField);
        addField(formPanel, "Status (1=Active, 0=Inactive)", statusField);
        addField(formPanel, "Note", noteField);
        addField(formPanel, "Start At (yyyy-mm-dd hh:mm:ss)", startAtField);
        addField(formPanel, "End At (yyyy-mm-dd hh:mm:ss)", endAtField);

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

    public void setCouponData(CouponModel coupon) {
        idField.setText(String.valueOf(coupon.getId()));
        codeField.setText(coupon.getCode());
        discountPercentField.setText(String.valueOf(coupon.getDiscountPercent()));
        statusField.setText(String.valueOf(coupon.getStatus()));
        noteField.setText(coupon.getNote());
        startAtField.setText(String.valueOf(coupon.getStartAt()));
        endAtField.setText(String.valueOf(coupon.getEndAt()));
    }

    public CouponModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        double discountPercent = discountPercentField.getText().isEmpty() ? 0.0
                : Double.parseDouble(discountPercentField.getText());
        String code = codeField.getText();
        int status = statusField.getText().isEmpty() ? 0 : Integer.parseInt(statusField.getText());
        String note = noteField.getText();
        Timestamp startAt = startAtField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(startAtField.getText());
        Timestamp endAt = endAtField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(endAtField.getText());

        return new CouponModel(id, discountPercent, code, status, note, startAt, endAt);
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
            codeField.setText("");
            discountPercentField.setText("");
            statusField.setText("");
            noteField.setText("");
            startAtField.setText("");
            endAtField.setText("");
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
    }
}
