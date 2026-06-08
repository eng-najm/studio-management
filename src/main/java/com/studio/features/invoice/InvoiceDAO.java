package com.studio.features.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.invoice.model.InvoiceModel;

public class InvoiceDAO extends BaseDAO {

    public Either<ArrayList<InvoiceModel>, Exception> getInvoices() {
        String sql = "SELECT * FROM INVOICE";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<InvoiceModel> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(InvoiceModel.fromResult(resultSet));
            }
            return Either.left(list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(e);
        }
    }

    public int addInvoice(InvoiceModel invoice) {
        String sql = "INSERT INTO INVOICE (ORDER_ID, INVOICE_DATE, NET_AMOUNT) VALUES (?, ?, ?)";
        return executeUpdate(sql,
                invoice.getOrderId(),
                invoice.getInvoiceDate(),
                invoice.getNetAmount());
    }

    public boolean updateInvoice(InvoiceModel invoice) {
        String sql = "UPDATE INVOICE SET INVOICE_DATE = ?, NET_AMOUNT = ? WHERE ORDER_ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, invoice.getInvoiceDate());
                ps.setDouble(2, invoice.getNetAmount());
                ps.setInt(3, invoice.getOrderId());
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

    public boolean deleteInvoice(int id) {
        String sql = "DELETE FROM INVOICE WHERE ORDER_ID = ?";
        return executeUpdate(sql, id) > 0;
    }
}
