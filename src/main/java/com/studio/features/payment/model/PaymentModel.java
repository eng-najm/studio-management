package com.studio.features.payment.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PaymentModel {

    private int paymentNumber;
    private int invoiceId;
    private double amount;
    private String method;
    private String referenceNo;
    private Timestamp paymentAt;

    public PaymentModel(int paymentNumber, int invoiceId, double amount, String method, String referenceNo, Timestamp paymentAt) {
        this.paymentNumber = paymentNumber;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.method = method;
        this.referenceNo = referenceNo;
        this.paymentAt = paymentAt;
    }

    public PaymentModel(int invoiceId, double amount, String method, String referenceNo, Timestamp paymentAt) {
        this(0, invoiceId, amount, method, referenceNo, paymentAt);
    }

    public int getPaymentNumber() { return paymentNumber; }
    public void setPaymentNumber(int paymentNumber) { this.paymentNumber = paymentNumber; }

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }

    public Timestamp getPaymentAt() { return paymentAt; }
    public void setPaymentAt(Timestamp paymentAt) { this.paymentAt = paymentAt; }

    public static PaymentModel fromResult(ResultSet rs) throws SQLException {
        return new PaymentModel(
                rs.getInt("PAYMENT_NUMBER"),
                rs.getInt("INVOICE_ID"),
                rs.getDouble("AMOUNT"),
                rs.getString("METHOD"),
                rs.getString("REFERENCE_NO"),
                rs.getTimestamp("PAYMENT_AT"));
    }
}
