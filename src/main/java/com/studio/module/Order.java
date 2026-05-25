package com.studio.module;

import java.util.Date;

enum OrderStatus {

}

public class Order {
    private int id;
    private Date startAt;
    private Date endAt;
    private String description;
    private int price;
    private double discountPercent = 1;
    private OrderStatus status;
    private int couponId;
    private int employeeId;

    public Order(int id, Date startAt, Date endAt, String description, int price, double discountPercent,
            OrderStatus status, int couponId, int employeeId) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.description = description;
        this.price = price;
        this.discountPercent = discountPercent;
        this.status = status;
        this.couponId = couponId;
        this.employeeId = employeeId;
    }

    public Order(Date startAt, Date endAt, String description, int price, OrderStatus status, int employeeId) {

        this.startAt = startAt;
        this.endAt = endAt;
        this.description = description;
        this.price = price;
        this.status = status;
        this.employeeId = employeeId;
    }

    public Order(Date startAt, Date endAt, String description, int price, double discountPercent, OrderStatus status,
            int couponId, int employeeId) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.description = description;
        this.price = price;
        this.discountPercent = discountPercent;
        this.status = status;
        this.couponId = couponId;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getcouponId() {
        return couponId;
    }

    public void setcouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getemployeeId() {
        return employeeId;
    }

    public void setemployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}
