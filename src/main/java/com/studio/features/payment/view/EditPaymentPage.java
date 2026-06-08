package com.studio.features.payment.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.model.ComboItem;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.payment.model.PaymentModel;

public class EditPaymentPage extends JPanel {
    AppFiled paymentNumberField = new AppFiled();
    JComboBox<ComboItem<?>> invoiceCombo = new JComboBox<>();
    AppFiled amountField = new AppFiled();
    AppFiled methodField = new AppFiled();
    AppFiled referenceNoField = new AppFiled();
    AppFiled paymentAtField = new AppFiled();

    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditPaymentPage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addField(formPanel, "Payment Number", paymentNumberField);
        addComboField(formPanel, "Invoice", invoiceCombo);
        addField(formPanel, "Amount", amountField);
        addField(formPanel, "Method", methodField);
        addField(formPanel, "Reference No", referenceNoField);
        addField(formPanel, "Payment At (yyyy-mm-dd hh:mm:ss)", paymentAtField);

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

    public void setInvoiceComboItems(List<ComboItem<Integer>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        model.addAll(items);
        invoiceCombo.setModel(model);
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

    public void setPaymentData(PaymentModel payment) {
        paymentNumberField.setText(String.valueOf(payment.getPaymentNumber()));
        paymentNumberField.setEditable(false);
        selectById(invoiceCombo, payment.getInvoiceId());
        invoiceCombo.setEnabled(false);
        amountField.setText(String.valueOf(payment.getAmount()));
        methodField.setText(payment.getMethod());
        referenceNoField.setText(payment.getReferenceNo() != null ? payment.getReferenceNo() : "");
        paymentAtField.setText(payment.getPaymentAt() != null ? payment.getPaymentAt().toString() : "");
    }

    public PaymentModel getCurrentData() {
        int paymentNumber = paymentNumberField.getText().isEmpty() ? 0
                : Integer.parseInt(paymentNumberField.getText());
        int invoiceId = getSelectedId(invoiceCombo);
        double amount = amountField.getText().isEmpty() ? 0.0
                : Double.parseDouble(amountField.getText());
        String method = methodField.getText();
        String referenceNo = referenceNoField.getText().isEmpty() ? null : referenceNoField.getText();
        Timestamp paymentAt = paymentAtField.getText().isEmpty() ? null
                : Timestamp.valueOf(paymentAtField.getText());
        return new PaymentModel(paymentNumber, invoiceId, amount, method, referenceNo, paymentAt);
    }

    public void setAdd(boolean isAdd) {
        if (isAdd) {
            paymentNumberField.setText("");
            paymentNumberField.setEditable(true);
            invoiceCombo.setEnabled(true);
            amountField.setText("");
            methodField.setText("");
            referenceNoField.setText("");
            paymentAtField.setText("");
            addButton.setVisible(true);
            applyChangeButton.setVisible(false);
            deleteButton.setVisible(false);
        } else {
            addButton.setVisible(false);
            applyChangeButton.setVisible(true);
            deleteButton.setVisible(true);
        }
    }

    public AppButton getApplyChangeButton() { return applyChangeButton; }
    public AppButton getAddButton() { return addButton; }
    public AppButton getDeleteButton() { return deleteButton; }
    public AppButton getBackButton() { return backButton; }
}
