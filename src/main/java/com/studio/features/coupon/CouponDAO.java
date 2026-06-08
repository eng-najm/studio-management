package com.studio.features.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.coupon.model.CouponModel;

public class CouponDAO extends BaseDAO {

    public Either<ArrayList<CouponModel>, Exception> getCoupons() {
        String sql = "SELECT * FROM COUPON";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<CouponModel> coupons = new ArrayList<>();
            while (resultSet.next()) {
                coupons.add(CouponModel.fromResult(resultSet));
            }
            return Either.left(coupons);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new Exception("Failed to fetch coupons"));
        }
    }

    public int addCoupon(CouponModel coupon) {
        String sql = "INSERT INTO COUPON (DISCOUNT_PERCENT, CODE, STATUS, NOTE, START_AT, END_AT) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                coupon.getDiscountPercent(),
                coupon.getCode(),
                coupon.getStatus(),
                coupon.getNote(),
                new Timestamp(coupon.getStartAt().getTime()),
                new Timestamp(coupon.getEndAt().getTime()));
    }

    public boolean updateCoupon(CouponModel coupon) {
        String sql = "UPDATE COUPON SET DISCOUNT_PERCENT = ?, CODE = ?, STATUS = ?, NOTE = ?, START_AT = ?, END_AT = ? WHERE ID = ?";

        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, coupon.getDiscountPercent());
                ps.setString(2, coupon.getCode());
                ps.setInt(3, coupon.getStatus());
                ps.setString(4, coupon.getNote());
                ps.setTimestamp(5, coupon.getStartAt());
                ps.setTimestamp(6, coupon.getEndAt());
                ps.setInt(7, coupon.getId());
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

    public boolean deleteCoupon(int id) {
        String sql = "DELETE FROM COUPON WHERE ID = ?";
        return executeUpdate(sql, id) > 0;
    }

}
