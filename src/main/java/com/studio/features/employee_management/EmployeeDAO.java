package com.studio.features.employee_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeeDAO extends BaseDAO {
    public Either<ArrayList<EmployeeModel>, Exception> getEmployees() {
        String sql = "SELECT p.*,e.* from Person p join Employee e on p.id = e.person_id";
        try {

            ResultSet resultSet = executeQuery(sql);
            ArrayList<EmployeeModel> employeeModels = new ArrayList<>();
            while (resultSet.next()) {
                employeeModels.add(EmployeeModel.fromResult(resultSet));
            }

            return Either.left(employeeModels);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new IllegalAccessException("UnKnowns"));
        }
    }

    public boolean updateEmployee(EmployeeModel employee) {
        String updatePersonSQL = "UPDATE Person SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, ADDRESS = ?, PHONE = ?, SEX = ? WHERE ID = ?";
        String updateEmployeeSQL = "UPDATE Employee SET SALARY = ?, ROLE = ?, USER_NAME = ?, USER_PASSWORD = ? WHERE PERSON_ID = ?"; // أزلت
                                                                                                                                     // HIRE_DATE
                                                                                                                                     // إذا
                                                                                                                                     // لم
                                                                                                                                     // تكن
                                                                                                                                     // تعدله

        try (Connection conn = DBHelper.connection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement psPerson = conn.prepareStatement(updatePersonSQL);
                    PreparedStatement psEmployee = conn.prepareStatement(updateEmployeeSQL)) {

                psPerson.setString(1, employee.getFirstName());
                psPerson.setString(2, employee.getMeddilName());
                psPerson.setString(3, employee.getLastName());
                psPerson.setString(4, employee.getAddress());
                psPerson.setString(5, employee.getPhone());
                psPerson.setString(6, employee.getSex() + "");
                psPerson.setInt(7, employee.getId());
                psPerson.executeUpdate();

                psEmployee.setDouble(1, employee.getSalary());
                psEmployee.setString(2, employee.getRole());
                psEmployee.setString(3, employee.getUserName());
                psEmployee.setString(4, employee.getUserPassword());
                psEmployee.setInt(5, employee.getId());
                psEmployee.executeUpdate();

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
