package com.studio.features.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.payment.model.PaymentModel;

public class PaymentDAO extends BaseDAO {

    public Either<ArrayList<PaymentModel>, Exception> getPayments() {
        String sql = "SELECT * FROM PAYMENT";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<PaymentModel> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(PaymentModel.fromResult(resultSet));
            }
            return Either.left(list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(e);
        }
    }

    public int addPayment(PaymentModel payment) {
        String sql = "INSERT INTO PAYMENT (PAYMENT_NUMBER, INVOICE_ID, AMOUNT, METHOD, REFERENCE_NO, PAYMENT_AT) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                payment.getPaymentNumber(),
                payment.getInvoiceId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getReferenceNo(),
                payment.getPaymentAt());
    }

    public boolean updatePayment(PaymentModel payment) {
        String sql = "UPDATE PAYMENT SET AMOUNT = ?, METHOD = ?, REFERENCE_NO = ?, PAYMENT_AT = ? WHERE PAYMENT_NUMBER = ? AND INVOICE_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, payment.getAmount());
                ps.setString(2, payment.getMethod());
                ps.setString(3, payment.getReferenceNo());
                ps.setTimestamp(4, payment.getPaymentAt());
                ps.setInt(5, payment.getPaymentNumber());
                ps.setInt(6, payment.getInvoiceId());
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

    public boolean deletePayment(int paymentNumber, int invoiceId) {
        String sql = "DELETE FROM PAYMENT WHERE PAYMENT_NUMBER = ? AND INVOICE_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, paymentNumber);
                ps.setInt(2, invoiceId);
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
}
