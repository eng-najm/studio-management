package com.studio.module;

import java.util.Date;

enum AttendanceStatus {
    PRESENT, // ? حاضر
    ABSENT, // ? غائب
    LATE, // ? متأخر
    EXCUSED, // ? غياب بعذر
    LEAVE // ? مستأذن / خرج بإذن
}

public class Attendance {

    private int id;
    private Date checkIn;
    private Date checkOut;
    private Date date;
    private AttendanceStatus status;
    private int employeeId;

    public Attendance(int id, int employeeId, Date checkIn, Date checkOut, Date date, AttendanceStatus status) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.date = date;
        this.status = status;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}