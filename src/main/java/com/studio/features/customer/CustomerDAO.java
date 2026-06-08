package com.studio.features.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.customer.model.CustomerModel;

public class CustomerDAO extends BaseDAO {
    public Either<ArrayList<CustomerModel>, Exception> getCustomers() {
        String sql = "SELECT * FROM Person WHERE PERSON_TYPE = 'CUSTOMER'";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<CustomerModel> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(CustomerModel.fromResult(resultSet));
            }
            return Either.left(customers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new IllegalAccessException("Unknown"));
        }
    }

    public int addCustomer(CustomerModel customer) {
        String sql = "INSERT INTO Person (FIRST_NAME, MIDDLE_NAME, LAST_NAME, ADDRESS, PHONE, SEX, PERSON_TYPE) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'CUSTOMER')";
        return executeUpdate(sql,
                customer.getFirstName(),
                customer.getMeddilName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhone(),
                String.valueOf(customer.getSex()));
    }

    public boolean updateCustomer(CustomerModel customer) {
        String sql = "UPDATE Person SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, ADDRESS = ?, PHONE = ?, SEX = ? WHERE ID = ?";

        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, customer.getFirstName());
                ps.setString(2, customer.getMeddilName());
                ps.setString(3, customer.getLastName());
                ps.setString(4, customer.getAddress());
                ps.setString(5, customer.getPhone());
                ps.setString(6, customer.getSex() + "");
                ps.setInt(7, customer.getId());
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
