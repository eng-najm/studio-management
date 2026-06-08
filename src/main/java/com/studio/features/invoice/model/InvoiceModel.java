package com.studio.features.invoice.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceModel {

    private int orderId;
    private String invoiceDate;
    private double netAmount;

    public InvoiceModel(int orderId, String invoiceDate, double netAmount) {
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
        this.netAmount = netAmount;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(String invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getNetAmount() { return netAmount; }
    public void setNetAmount(double netAmount) { this.netAmount = netAmount; }

    public static InvoiceModel fromResult(ResultSet rs) throws SQLException {
        return new InvoiceModel(
                rs.getInt("ORDER_ID"),
                rs.getString("INVOICE_DATE"),
                rs.getDouble("NET_AMOUNT"));
    }
}
