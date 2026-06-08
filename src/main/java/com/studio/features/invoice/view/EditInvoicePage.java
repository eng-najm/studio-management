package com.studio.features.invoice.view;

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
import com.studio.features.invoice.model.InvoiceModel;

public class EditInvoicePage extends JPanel {
    private int currentOrderId;
    AppFiled netAmountField = new AppFiled();
    private AppLable orderIdLabel = new AppLable("");
    private AppLable invoiceDateLabel = new AppLable("");

    private AppButton applyChangeButton;
    private AppButton deleteButton;
    private AppButton backButton;

    public EditInvoicePage() {
        this.setLayout(new BorderLayout());
        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        deleteButton = new AppButton("Delete");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        addLabelField(formPanel, "Order ID", orderIdLabel);
        addLabelField(formPanel, "Invoice Date", invoiceDateLabel);
        addField(formPanel, "Net Amount", netAmountField);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
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

    private void addLabelField(JPanel panel, String labelText, AppLable value) {
        AppLable label = new AppLable(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        value.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(value);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void setInvoiceData(InvoiceModel invoice) {
        currentOrderId = invoice.getOrderId();
        orderIdLabel.setText(String.valueOf(invoice.getOrderId()));
        invoiceDateLabel.setText(invoice.getInvoiceDate());
        netAmountField.setText(String.valueOf(invoice.getNetAmount()));
    }

    public InvoiceModel getCurrentData() {
        String invoiceDate = invoiceDateLabel.getText();
        double netAmount = netAmountField.getText().isEmpty() ? 0.0
                : Double.parseDouble(netAmountField.getText());
        return new InvoiceModel(currentOrderId, invoiceDate, netAmount);
    }

    public AppButton getApplyChangeButton() { return applyChangeButton; }
    public AppButton getBackButton() { return backButton; }
    public AppButton getDeleteButton() { return deleteButton; }
}
