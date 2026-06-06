package com.studio.features.dashboard.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.employee_management.view.EmployeeTable;

public class NavigationController {
    DashboardPage dashboardPage;

    public NavigationController(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;

    }

    public void init() {
        addEditButtonListener();
        dashboardPage.getEmpButton().addActionListener(e -> goTo(AppRoutes.EMPLOYEE_MANAGEMENT));
        dashboardPage.getEditEmployeePage().getBackButton().addActionListener(e -> goTo(AppRoutes.EMPLOYEE_MANAGEMENT));
    }

    void goTo(String route) {
        dashboardPage.getCardLayout().show(dashboardPage.getPanale(), route);
    }

    private void addEditButtonListener() {

        JTable table = dashboardPage.getEmployeePage().getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.rowAtPoint(e.getPoint());

                if (row >= 0) {

                    EmployeeTable emp = (EmployeeTable) table.getModel();
                    dashboardPage.getEditEmployeePage().setEmployeeData(emp.getEmployeeAt(row));
                    goTo(AppRoutes.EDITE_EMPLOYEE);

                }
            }
        });
    }

}
