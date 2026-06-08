package com.studio.features.attendance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class AttendanceModel {

    private int id;
    private int empId;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Date date;
    private int status;

    public AttendanceModel(int id, int empId, Timestamp checkIn, Timestamp checkOut, Date date, int status) {
        this.id = id;
        this.empId = empId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) { this.status = status; }

public static AttendanceModel fromResult(ResultSet rs) throws SQLException {
        return new AttendanceModel(
                rs.getInt("ID"),
                rs.getInt("EMP_ID"),
                rs.getTimestamp("CHECK_IN"),
                rs.getTimestamp("CHECK_OUT"),
                rs.getDate("DATE"),
                rs.getInt("STATUS"));
    }
}
