package com.studio.features.coupon.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CouponModel {

    private int id;
    private double discountPercent;
    private String code;
    private int status;
    private String note;
    private Timestamp startAt;
    private Timestamp endAt;

    public CouponModel(int id, double discountPercent, String code, int status, String note, Timestamp startAt,
            Timestamp endAt) {
        this.id = id;
        this.discountPercent = discountPercent;
        this.code = code;
        this.status = status;
        this.note = note;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public CouponModel(double discountPercent, String code, int status, String note, Timestamp startAt,
            Timestamp endAt) {
        this(0, discountPercent, code, status, note, startAt, endAt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    public static CouponModel fromResult(ResultSet rs) throws SQLException {
        return new CouponModel(
                rs.getInt("ID"),
                rs.getDouble("DISCOUNT_PERCENT"),
                rs.getString("CODE"),
                rs.getInt("STATUS"),
                rs.getString("NOTE"),
                rs.getTimestamp("START_AT"),
                rs.getTimestamp("END_AT"));
    }

}
