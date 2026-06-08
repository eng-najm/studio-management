package com.studio.features.coupon.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.coupon.model.CouponModel;

public class CouponTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Code", "Discount %", "Status", "Note", "Start At", "End At" };
    private final List<CouponModel> coupons;

    public CouponTable(List<CouponModel> coupons) {
        this.coupons = coupons;
    }

    @Override
    public int getRowCount() {
        return coupons.size();
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
        CouponModel coupon = coupons.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return coupon.getId();
            case 1:
                return coupon.getCode();
            case 2:
                return coupon.getDiscountPercent();
            case 3:
                return coupon.getStatus() == 1 ? "Active" : "Inactive";
            case 4:
                return coupon.getNote();
            case 5:
                return coupon.getStartAt();
            case 6:
                return coupon.getEndAt();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public CouponModel getCouponAt(int rowIndex) {
        return coupons.get(rowIndex);
    }
}
