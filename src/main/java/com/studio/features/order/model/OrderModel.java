package com.studio.features.order.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderModel {

    private int id;
    private int customerId;
    private String customerName;
    private int receptionistId;
    private String receptionistName;
    private Integer couponId;
    private double discountPercent;
    private Timestamp startAt;
    private Timestamp endAt;
    private Timestamp orderAt;
    private int status;
    private int price;
    private String description;
    private String orderType;

    public OrderModel(int id, int customerId, String customerName, int receptionistId, String receptionistName,
            Integer couponId, double discountPercent, Timestamp startAt, Timestamp endAt, Timestamp orderAt,
            int status, int price, String description, String orderType) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.receptionistId = receptionistId;
        this.receptionistName = receptionistName;
        this.couponId = couponId;
        this.discountPercent = discountPercent;
        this.startAt = startAt;
        this.endAt = endAt;
        this.orderAt = orderAt;
        this.status = status;
        this.price = price;
        this.description = description;
        this.orderType = orderType;
    }

    public OrderModel(int customerId, int receptionistId, Integer couponId, double discountPercent,
            Timestamp startAt, Timestamp endAt, int status, int price, String description, String orderType) {
        this(0, customerId, null, receptionistId, null, couponId, discountPercent, startAt, endAt, null,
                status, price, description, orderType);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public int getReceptionistId() { return receptionistId; }
    public void setReceptionistId(int receptionistId) { this.receptionistId = receptionistId; }
    public String getReceptionistName() { return receptionistName; }
    public void setReceptionistName(String receptionistName) { this.receptionistName = receptionistName; }
    public Integer getCouponId() { return couponId; }
    public void setCouponId(Integer couponId) { this.couponId = couponId; }
    public double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }
    public Timestamp getStartAt() { return startAt; }
    public void setStartAt(Timestamp startAt) { this.startAt = startAt; }
    public Timestamp getEndAt() { return endAt; }
    public void setEndAt(Timestamp endAt) { this.endAt = endAt; }
    public Timestamp getOrderAt() { return orderAt; }
    public void setOrderAt(Timestamp orderAt) { this.orderAt = orderAt; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public static OrderModel fromResult(ResultSet rs) throws SQLException {
        return new OrderModel(
                rs.getInt("ID"),
                rs.getInt("CUSTOMER_ID"),
                rs.getString("CUST_FIRST_NAME") + " " + rs.getString("CUST_LAST_NAME"),
                rs.getInt("RECEPTIONIST_ID"),
                rs.getString("RECEPT_FIRST_NAME") + " " + rs.getString("RECEPT_LAST_NAME"),
                rs.getObject("COUPON_ID") != null ? rs.getInt("COUPON_ID") : null,
                rs.getDouble("DISCOUNT_PERCENT"),
                rs.getTimestamp("START_AT"),
                rs.getTimestamp("END_AT"),
                rs.getTimestamp("ORDER_AT"),
                rs.getInt("STATUS"),
                rs.getInt("PRICE"),
                rs.getString("DESCRIPTION"),
                rs.getString("ORDER_TYPE"));
    }

}
