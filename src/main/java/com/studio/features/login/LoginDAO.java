package com.studio.features.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.studio.core.BaseDAO;
import com.studio.core.Either;
import com.studio.features.login.model.Employee;

public class LoginDAO extends BaseDAO {
    public Either<Employee, Exception> getEmployee(String userName, String userPassword) {
        String sql = "SELECT p.*,e.* from Person p join Employee e on p.id = e.person_id where e.USER_NAME=? and e.USER_PASSWORD=?";
        try {
            ResultSet resultSet = executeQuery(sql, userName, userPassword);
            resultSet.next();
            Employee emp = Employee.fromResult(resultSet);
            return Either.left(emp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new IllegalAccessException("userName or Password"));
        }
    }
}
