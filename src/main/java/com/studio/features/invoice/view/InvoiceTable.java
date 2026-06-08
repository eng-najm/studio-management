package com.studio.features.invoice.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.invoice.model.InvoiceModel;

public class InvoiceTable extends AbstractTableModel {
    private final String[] columnNames = { "Order ID", "Invoice Date", "Net Amount" };
    private final List<InvoiceModel> invoices;

    public InvoiceTable(List<InvoiceModel> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
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
        InvoiceModel inv = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0: return inv.getOrderId();
            case 1: return inv.getInvoiceDate();
            case 2: return inv.getNetAmount();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public InvoiceModel getInvoiceAt(int rowIndex) {
        return invoices.get(rowIndex);
    }
}
