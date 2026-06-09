package com.studio.features.employee_management.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.util.List;

import com.studio.core.ComboItemsProvider;
import com.studio.core.Either;
import com.studio.core.FieldValidator;
import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.employee_management.EmployeeDAO;
import com.studio.features.employee_management.model.EmployeeModel;
import com.studio.features.employee_management.view.EditEmployeePage;
import com.studio.features.employee_management.view.EmployeePage;
import com.studio.features.employee_management.view.EmployeeTable;

public class EmployeeController {
    private EmployeePage employeePage;
    private EditEmployeePage editEmployeePage;
    DashboardPage route;
    private EmployeeDAO employeeDAO;
    private ComboItemsProvider comboProvider;

    public EmployeeController(EmployeePage employeePage, EditEmployeePage editEmployeePage, DashboardPage route) {
        this.employeePage = employeePage;
        this.editEmployeePage = editEmployeePage;
        this.route = route;
        employeeDAO = new EmployeeDAO();
        comboProvider = new ComboItemsProvider();
        init();
    }

    void init() {
        fetchEployees();
        editEmployeePage.getApplyChangeButton().addActionListener(e -> editEmployee());
        addCreateEmployeeButton();
        editEmployeePage.getBackButton().addActionListener((e) -> route.goTo(AppRoutes.EMPLOYEE_MANAGEMENT));
        addEditButtonListener();
        editEmployeePage.getAddButton().addActionListener((e) -> addEmployee());
    }

    void addCreateEmployeeButton() {
        employeePage.getCreateButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                editEmployeePage.setEmpTypeComboItems(comboProvider.getEmployeeTypeItems());
                route.goTo(AppRoutes.EDITE_EMPLOYEE);
                editEmployeePage.setAdd(true);
            }

        });
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
        List<String> errors = editEmployeePage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(employeePage, errors);
            return;
        }
        boolean result = employeeDAO.updateEmployee(editEmployeePage.getCurrentData());
        if (result) {
            JOptionPane.showMessageDialog(employeePage, "SuccessFull");

        } else {
            JOptionPane.showMessageDialog(employeePage, "Filed");

        }
    }

    public void addEmployee() {
        List<String> errors = editEmployeePage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(employeePage, errors);
            return;
        }
        int row = employeeDAO.addEmployee(editEmployeePage.getCurrentData());
        if (row > 0) {
            JOptionPane.showMessageDialog(employeePage, "SuccessFull");
        } else {
            JOptionPane.showMessageDialog(employeePage, "Filed");

        }
    }

    private void addEditButtonListener() {

        JTable table = employeePage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());

                if (row >= 0) {

                    EmployeeTable emp = (EmployeeTable) table.getModel();
                    editEmployeePage.setEmpTypeComboItems(comboProvider.getEmployeeTypeItems());
                    editEmployeePage.setEmployeeData(emp.getEmployeeAt(row));
                    route.goTo(AppRoutes.EDITE_EMPLOYEE);
                    editEmployeePage.setAdd(false);

                }
            }
        });
    }

}
