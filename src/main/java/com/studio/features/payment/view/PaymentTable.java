package com.studio.features.payment.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.payment.model.PaymentModel;

public class PaymentTable extends AbstractTableModel {
    private final String[] columnNames = { "Payment #", "Invoice ID", "Amount", "Method", "Reference No", "Payment At" };
    private final List<PaymentModel> payments;

    public PaymentTable(List<PaymentModel> payments) {
        this.payments = payments;
    }

    @Override
    public int getRowCount() {
        return payments.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PaymentModel pmt = payments.get(rowIndex);
        switch (columnIndex) {
            case 0: return pmt.getPaymentNumber();
            case 1: return pmt.getInvoiceId();
            case 2: return pmt.getAmount();
            case 3: return pmt.getMethod();
            case 4: return pmt.getReferenceNo();
            case 5: return pmt.getPaymentAt();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public PaymentModel getPaymentAt(int rowIndex) {
        return payments.get(rowIndex);
    }
}
