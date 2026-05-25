package com.studio.module;

import java.util.Date;

public class Payments {
    private int inoviceId;
    private int paymentNaumber;
    private Date paidAt;
    private String method;
    private double amount;
    private String referenceNo;

    public Payments(int inoviceId, int paymentNaumber, Date paidAt, String method, double amount, String referenceNo) {
        this.inoviceId = inoviceId;
        this.paymentNaumber = paymentNaumber;
        this.paidAt = paidAt;
        this.method = method;
        this.amount = amount;
        this.referenceNo = referenceNo;
    }

    public int getinoviceId() {
        return inoviceId;
    }

    public void setinoviceId(int inoviceId) {
        this.inoviceId = inoviceId;
    }

    public int getPaymentNaumber() {
        return paymentNaumber;
    }

    public void setPaymentNaumber(int paymentNaumber) {
        this.paymentNaumber = paymentNaumber;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

}