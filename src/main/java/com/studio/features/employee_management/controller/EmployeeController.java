package com.studio.features.employee_management.controller;

import java.util.ArrayList;

import com.studio.core.Either;
import com.studio.features.employee_management.EmployeeDAO;
import com.studio.features.employee_management.model.EmployeeModel;
import com.studio.features.employee_management.view.EmployeePage;

public class EmployeeController {
    private EmployeePage employeePage;
    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeePage employeePage) {
        this.employeePage = employeePage;
        employeeDAO = new EmployeeDAO();
        init();
    }

    void init() {
        Either<ArrayList<EmployeeModel>, Exception> result = employeeDAO.getEmployees();
        if (result.isLeft()) {
            employeePage.populateEmployeeList(result.getLeft());
        } else {
            employeePage.populateEmployeeList(new ArrayList<>());
            System.err.println("Failed to fetch employees: " + result.getRight().getMessage());
        }
    }

}
