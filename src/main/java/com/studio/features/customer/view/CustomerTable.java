package com.studio.features.customer.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.customer.model.CustomerModel;

public class CustomerTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "First Name", "Middle Name", "Last Name",
            "Sex", "Phone", "Address" };
    private final List<CustomerModel> customers;

    public CustomerTable(List<CustomerModel> customers) {
        this.customers = customers;
    }

    @Override
    public int getRowCount() {
        return customers.size();
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
        CustomerModel customer = customers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getFirstName();
            case 2:
                return customer.getMeddilName();
            case 3:
                return customer.getLastName();
            case 4:
                return customer.getSex();
            case 5:
                return customer.getPhone();
            case 6:
                return customer.getAddress();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public CustomerModel getCustomerAt(int rowIndex) {
        return customers.get(rowIndex);
    }
}
