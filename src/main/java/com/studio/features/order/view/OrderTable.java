package com.studio.features.order.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.order.model.OrderModel;

public class OrderTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Customer", "Receptionist", "Type",
            "Price", "Status", "Order At", "Description" };
    private final List<OrderModel> orders;

    public OrderTable(List<OrderModel> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
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
        OrderModel order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getId();
            case 1:
                return order.getCustomerName();
            case 2:
                return order.getReceptionistName();
            case 3:
                return order.getOrderType();
            case 4:
                return order.getPrice();
            case 5:
                return statusLabel(order.getStatus());
            case 6:
                return order.getOrderAt();
            case 7:
                return order.getDescription();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private String statusLabel(int status) {
        switch (status) {
            case 0: return "Pending";
            case 1: return "Active";
            case 2: return "Canceled";
            case 3: return "Complete";
            default: return "Unknown";
        }
    }

    public OrderModel getOrderAt(int rowIndex) {
        return orders.get(rowIndex);
    }
}
