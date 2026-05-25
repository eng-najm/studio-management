package com.studio.module;

import java.util.Date;

enum CodeStatus {
    ACTIVE,
    DISACTIVE,
}

public class Coupon {
    private int id;
    private Date startDate;
    private Date endDate;
    private double discount;
    private String code;
    private CodeStatus codeStatus;
    private String note;

    public Coupon(int id, Date startDate, Date endDate, double discount, String code, CodeStatus codeStatus,
            String note) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.code = code;
        this.codeStatus = codeStatus;
        this.note = note;
    }

    public Coupon(Date startDate, Date endDate, double discount, String code, CodeStatus codeStatus, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.code = code;
        this.codeStatus = codeStatus;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getstartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CodeStatus getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(CodeStatus codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
