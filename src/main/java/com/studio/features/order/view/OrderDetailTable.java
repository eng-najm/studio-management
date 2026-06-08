package com.studio.features.order.view;

import javax.swing.table.AbstractTableModel;

import com.studio.features.order.model.OrderModel;

public class OrderDetailTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Customer", "Receptionist", "Coupon ID",
            "Discount %", "Start At", "End At", "Order At", "Status", "Price", "Description", "Type" };
    private OrderModel order;

    public void setOrderData(OrderModel order) {
        this.order = order;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return order != null ? 1 : 0;
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
        if (order == null) return null;
        switch (columnIndex) {
            case 0: return order.getId();
            case 1: return order.getCustomerName();
            case 2: return order.getReceptionistName();
            case 3: return order.getCouponId();
            case 4: return order.getDiscountPercent();
            case 5: return order.getStartAt();
            case 6: return order.getEndAt();
            case 7: return order.getOrderAt();
            case 8: return statusLabel(order.getStatus());
            case 9: return order.getPrice();
            case 10: return order.getDescription();
            case 11: return order.getOrderType();
            default: return null;
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
}
