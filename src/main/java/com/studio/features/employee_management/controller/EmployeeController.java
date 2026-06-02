package com.studio.features.employee_management.controller;

import com.studio.features.employee_management.EmployeeDAO;
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
        employeePage.populateEmployeeList(employeeDAO.getEmployees().getLeft());
    }

}
