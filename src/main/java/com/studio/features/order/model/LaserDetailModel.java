package com.studio.features.order.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LaserDetailModel {

    private int orderId;
    private String materials;
    private String designFileUrl;

    public LaserDetailModel(int orderId, String materials, String designFileUrl) {
        this.orderId = orderId;
        this.materials = materials;
        this.designFileUrl = designFileUrl;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getMaterials() { return materials; }
    public void setMaterials(String materials) { this.materials = materials; }
    public String getDesignFileUrl() { return designFileUrl; }
    public void setDesignFileUrl(String designFileUrl) { this.designFileUrl = designFileUrl; }

    public static LaserDetailModel fromResult(ResultSet rs) throws SQLException {
        return new LaserDetailModel(
                rs.getInt("ORDER_ID"),
                rs.getString("MATERIALS"),
                rs.getString("DESIGN_FILE_URL"));
    }

}
