package com.studio.features.employee_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
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
}
