package com.studio.module;

public class Invoice {
    private int orderId;
    private String invoiceDate;
    private double netAmount;

    public Invoice(int orderId, String invoiceDate, double netAmount) {
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
        this.netAmount = netAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

}
