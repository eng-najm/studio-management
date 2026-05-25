package com.studio.module;

import java.util.Date;

public class Invoice {
    private int id;
    private int orderId;
    private Date InvoicedAt;
    private double netAmount;

    public Invoice(int id, int orderId, Date invoicedAt, double netAmount) {
        this.id = id;
        this.orderId = orderId;
        InvoicedAt = invoicedAt;
        this.netAmount = netAmount;
    }

    public Invoice(int orderId, Date invoicedAt, double netAmount) {
        this.orderId = orderId;
        InvoicedAt = invoicedAt;
        this.netAmount = netAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getorderId() {
        return orderId;
    }

    public void setorderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getInvoicedAt() {
        return InvoicedAt;
    }

    public void setInvoicedAt(Date invoicedAt) {
        InvoicedAt = invoicedAt;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

}
