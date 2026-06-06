package com.studio.features.employee_management.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.studio.core.Either;
import com.studio.features.employee_management.EmployeeDAO;
import com.studio.features.employee_management.model.EmployeeModel;
import com.studio.features.employee_management.view.EditEmployeePage;
import com.studio.features.employee_management.view.EmployeePage;

public class EmployeeController {
    private EmployeePage employeePage;
    private EditEmployeePage editEmployeePage;
    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeePage employeePage, EditEmployeePage editEmployeePage) {
        this.employeePage = employeePage;
        this.editEmployeePage = editEmployeePage;
        employeeDAO = new EmployeeDAO();
        init();
    }

    void init() {
        fetchEployees();
        editEmployeePage.getApplyChangeButton().addActionListener(e -> editEmployee());
    }

    public void fetchEployees() {
        Either<ArrayList<EmployeeModel>, Exception> result = employeeDAO.getEmployees();
        if (result.isLeft()) {
            employeePage.populateEmployeeList(result.getLeft());
        } else {
            employeePage.populateEmployeeList(new ArrayList<>());
            System.err.println("Failed to fetch employees: " + result.getRight().getMessage());
        }
    }

    public void editEmployee() {
        boolean result = employeeDAO.updateEmployee(editEmployeePage.getCurrentData());
        if (result) {
            JOptionPane.showMessageDialog(employeePage, "SuccessFull");

        } else {
            JOptionPane.showMessageDialog(employeePage, "Filed");

        }
    }

}
