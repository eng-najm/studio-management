package com.studio.features.order.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ImageSessionDetailModel {

    private int orderId;
    private String sessionType;
    private Timestamp scheduledAt;
    private int duration;
    private int photographerId;
    private String photographerName;

    public ImageSessionDetailModel(int orderId, String sessionType, Timestamp scheduledAt, int duration,
            int photographerId, String photographerName) {
        this.orderId = orderId;
        this.sessionType = sessionType;
        this.scheduledAt = scheduledAt;
        this.duration = duration;
        this.photographerId = photographerId;
        this.photographerName = photographerName;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getSessionType() { return sessionType; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }
    public Timestamp getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(Timestamp scheduledAt) { this.scheduledAt = scheduledAt; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }
    public String getPhotographerName() { return photographerName; }
    public void setPhotographerName(String photographerName) { this.photographerName = photographerName; }

    public static ImageSessionDetailModel fromResult(ResultSet rs) throws SQLException {
        return new ImageSessionDetailModel(
                rs.getInt("ORDER_ID"),
                rs.getString("SESSION_TYPE"),
                rs.getTimestamp("SCHEDULED_AT"),
                rs.getInt("DURATION"),
                rs.getInt("PHOTOGRAPHER_ID"),
                rs.getString("PHOTO_FIRST_NAME") + " " + rs.getString("PHOTO_LAST_NAME"));
    }

}
