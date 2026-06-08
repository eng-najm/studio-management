package com.studio.features.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.order.model.ImageSessionDetailModel;
import com.studio.features.order.model.LaserDetailModel;
import com.studio.features.order.model.OrderModel;
import com.studio.features.order.model.PrintDetailModel;

public class OrderDAO extends BaseDAO {

    public Either<ArrayList<OrderModel>, Exception> getOrders() {
        String sql = "SELECT o.*, " +
                "c.FIRST_NAME AS CUST_FIRST_NAME, c.LAST_NAME AS CUST_LAST_NAME, " +
                "r.FIRST_NAME AS RECEPT_FIRST_NAME, r.LAST_NAME AS RECEPT_LAST_NAME " +
                "FROM \"Order\" o " +
                "JOIN PERSON c ON o.CUSTOMER_ID = c.ID " +
                "JOIN EMPLOYEE re ON o.RECEPTIONIST_ID = re.PERSON_ID " +
                "JOIN PERSON r ON re.PERSON_ID = r.ID " +
                "ORDER BY o.ID DESC";
        try {
            ResultSet rs = executeQuery(sql);
            ArrayList<OrderModel> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(OrderModel.fromResult(rs));
            }
            return Either.left(orders);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new Exception("Failed to fetch orders"));
        }
    }

    public Either<OrderModel, Exception> getOrderById(int id) {
        String sql = "SELECT o.*, " +
                "c.FIRST_NAME AS CUST_FIRST_NAME, c.LAST_NAME AS CUST_LAST_NAME, " +
                "r.FIRST_NAME AS RECEPT_FIRST_NAME, r.LAST_NAME AS RECEPT_LAST_NAME " +
                "FROM \"Order\" o " +
                "JOIN PERSON c ON o.CUSTOMER_ID = c.ID " +
                "JOIN EMPLOYEE re ON o.RECEPTIONIST_ID = re.PERSON_ID " +
                "JOIN PERSON r ON re.PERSON_ID = r.ID " +
                "WHERE o.ID = ?";
        try {
            ResultSet rs = executeQuery(sql, id);
            if (rs.next()) {
                return Either.left(OrderModel.fromResult(rs));
            }
            return Either.right(new Exception("Order not found"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new Exception("Failed to fetch order"));
        }
    }

    public Either<?, Exception> getTypeSpecificDetail(int orderId, String orderType) {
        try {
            switch (orderType) {
                case "LASER": {
                    String sql = "SELECT * FROM LASER WHERE ORDER_ID = ?";
                    ResultSet rs = executeQuery(sql, orderId);
                    if (rs.next()) {
                        return Either.left(LaserDetailModel.fromResult(rs));
                    }
                    return Either.right(new Exception("Laser detail not found"));
                }
                case "PRINT": {
                    String sql = "SELECT * FROM PRINT WHERE ORDER_ID = ?";
                    ResultSet rs = executeQuery(sql, orderId);
                    if (rs.next()) {
                        return Either.left(PrintDetailModel.fromResult(rs));
                    }
                    return Either.right(new Exception("Print detail not found"));
                }
                case "IMAGE": {
                    String sql = "SELECT s.*, p.FIRST_NAME AS PHOTO_FIRST_NAME, p.LAST_NAME AS PHOTO_LAST_NAME " +
                            "FROM IMAGE_SESSION s " +
                            "JOIN EMPLOYEE e ON s.PHOTOGRAPHER_ID = e.PERSON_ID " +
                            "JOIN PERSON p ON e.PERSON_ID = p.ID " +
                            "WHERE s.ORDER_ID = ?";
                    ResultSet rs = executeQuery(sql, orderId);
                    if (rs.next()) {
                        return Either.left(ImageSessionDetailModel.fromResult(rs));
                    }
                    return Either.right(new Exception("Image session detail not found"));
                }
                default:
                    return Either.right(new Exception("Unknown order type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new Exception("Failed to fetch type detail"));
        }
    }

    public int addOrder(OrderModel order) {
        String sql = "DECLARE " +
                "  new_id NUMBER; " +
                "BEGIN " +
                "  INSERT INTO \"Order\" (CUSTOMER_ID, RECEPTIONIST_ID, COUPON_ID, DISCOUNT_PERCENT, " +
                "    START_AT, END_AT, ORDER_AT, STATUS, PRICE, DESCRIPTION, ORDER_TYPE) " +
                "  VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?, ?) " +
                "  RETURNING ID INTO new_id; " +
                "  ? := new_id; " +
                "END;";
        try (Connection conn = DBHelper.connection();
                java.sql.CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, order.getCustomerId());
            cs.setInt(2, order.getReceptionistId());
            if (order.getCouponId() != null) {
                cs.setInt(3, order.getCouponId());
            } else {
                cs.setNull(3, Types.INTEGER);
            }
            cs.setDouble(4, order.getDiscountPercent());
            cs.setTimestamp(5, order.getStartAt());
            cs.setTimestamp(6, order.getEndAt());
            cs.setInt(7, order.getStatus());
            cs.setInt(8, order.getPrice());
            cs.setString(9, order.getDescription());
            cs.setString(10, order.getOrderType());
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            return cs.getInt(11);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addLaserDetail(int orderId, LaserDetailModel detail) {
        String sql = "INSERT INTO LASER (ORDER_ID, MATERIALS, DESIGN_FILE_URL) VALUES (?, ?, ?)";
        return executeUpdate(sql, orderId, detail.getMaterials(), detail.getDesignFileUrl());
    }

    public int addPrintDetail(int orderId, PrintDetailModel detail) {
        String sql = "INSERT INTO PRINT (ORDER_ID, PRINT_TYPE, QTY, PAPER_SIZE, PAPER_TYPE, FILE_PATH) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, orderId, detail.getPrintType(), detail.getQty(),
                detail.getPaperSize(), detail.getPaperType(), detail.getFilePath());
    }

    public int addImageSessionDetail(int orderId, ImageSessionDetailModel detail) {
        String sql = "INSERT INTO IMAGE_SESSION (ORDER_ID, SESSION_TYPE, SCHEDULED_AT, DURATION, PHOTOGRAPHER_ID) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql, orderId, detail.getSessionType(), detail.getScheduledAt(),
                detail.getDuration(), detail.getPhotographerId());
    }

    public boolean updateOrder(OrderModel order) {
        String sql = "UPDATE \"Order\" SET CUSTOMER_ID = ?, RECEPTIONIST_ID = ?, COUPON_ID = ?, " +
                "DISCOUNT_PERCENT = ?, START_AT = ?, END_AT = ?, STATUS = ?, PRICE = ?, DESCRIPTION = ? WHERE ID = ?";

        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, order.getCustomerId());
                ps.setInt(2, order.getReceptionistId());
                if (order.getCouponId() != null) {
                    ps.setInt(3, order.getCouponId());
                } else {
                    ps.setNull(3, Types.INTEGER);
                }
                ps.setDouble(4, order.getDiscountPercent());
                ps.setTimestamp(5, order.getStartAt());
                ps.setTimestamp(6, order.getEndAt());
                ps.setInt(7, order.getStatus());
                ps.setInt(8, order.getPrice());
                ps.setString(9, order.getDescription());
                ps.setInt(10, order.getId());
                ps.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateLaserDetail(LaserDetailModel detail) {
        String sql = "UPDATE LASER SET MATERIALS = ?, DESIGN_FILE_URL = ? WHERE ORDER_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, detail.getMaterials());
                ps.setString(2, detail.getDesignFileUrl());
                ps.setInt(3, detail.getOrderId());
                ps.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updatePrintDetail(PrintDetailModel detail) {
        String sql = "UPDATE PRINT SET PRINT_TYPE = ?, QTY = ?, PAPER_SIZE = ?, PAPER_TYPE = ?, FILE_PATH = ? WHERE ORDER_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, detail.getPrintType());
                ps.setInt(2, detail.getQty());
                ps.setString(3, detail.getPaperSize());
                ps.setString(4, detail.getPaperType());
                ps.setString(5, detail.getFilePath());
                ps.setInt(6, detail.getOrderId());
                ps.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateImageSessionDetail(ImageSessionDetailModel detail) {
        String sql = "UPDATE IMAGE_SESSION SET SESSION_TYPE = ?, SCHEDULED_AT = ?, DURATION = ?, PHOTOGRAPHER_ID = ? WHERE ORDER_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, detail.getSessionType());
                ps.setTimestamp(2, detail.getScheduledAt());
                ps.setInt(3, detail.getDuration());
                ps.setInt(4, detail.getPhotographerId());
                ps.setInt(5, detail.getOrderId());
                ps.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(int id) {
        String typeSql = "SELECT ORDER_TYPE FROM \"Order\" WHERE ID = ?";
        String deleteLaser = "DELETE FROM LASER WHERE ORDER_ID = ?";
        String deletePrint = "DELETE FROM PRINT WHERE ORDER_ID = ?";
        String deleteImage = "DELETE FROM IMAGE_SESSION WHERE ORDER_ID = ?";
        String deleteOrder = "DELETE FROM \"Order\" WHERE ID = ?";

        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);

            try {
                String orderType = null;
                try (PreparedStatement ps = conn.prepareStatement(typeSql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        orderType = rs.getString("ORDER_TYPE");
                    }
                }

                if ("LASER".equals(orderType)) {
                    try (PreparedStatement ps = conn.prepareStatement(deleteLaser)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                } else if ("PRINT".equals(orderType)) {
                    try (PreparedStatement ps = conn.prepareStatement(deletePrint)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                } else if ("IMAGE".equals(orderType)) {
                    try (PreparedStatement ps = conn.prepareStatement(deleteImage)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(deleteOrder)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
