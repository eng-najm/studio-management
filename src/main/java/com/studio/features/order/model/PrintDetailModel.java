package com.studio.features.order.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintDetailModel {

    private int orderId;
    private String printType;
    private int qty;
    private String paperSize;
    private String paperType;
    private String filePath;

    public PrintDetailModel(int orderId, String printType, int qty, String paperSize, String paperType, String filePath) {
        this.orderId = orderId;
        this.printType = printType;
        this.qty = qty;
        this.paperSize = paperSize;
        this.paperType = paperType;
        this.filePath = filePath;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getPrintType() { return printType; }
    public void setPrintType(String printType) { this.printType = printType; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public String getPaperSize() { return paperSize; }
    public void setPaperSize(String paperSize) { this.paperSize = paperSize; }
    public String getPaperType() { return paperType; }
    public void setPaperType(String paperType) { this.paperType = paperType; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public static PrintDetailModel fromResult(ResultSet rs) throws SQLException {
        return new PrintDetailModel(
                rs.getInt("ORDER_ID"),
                rs.getString("PRINT_TYPE"),
                rs.getInt("QTY"),
                rs.getString("PAPER_SIZE"),
                rs.getString("PAPER_TYPE"),
                rs.getString("FILE_PATH"));
    }

}
