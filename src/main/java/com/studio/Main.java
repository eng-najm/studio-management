package com.studio;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.studio.Main;
import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.employee_management.view.EmployeePage;
import com.studio.features.login.controller.LoginController;
import com.studio.features.login.view.LoginPage;

public class Main extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    public Main() {
        setTitle("App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        LoginPage loginPanel = new LoginPage();
        EmployeePage employeePage = new EmployeePage();
        DashboardPage dashboardPage = new DashboardPage();

        container.add(loginPanel, AppRoutes.LOGIN);
        container.add(dashboardPage, AppRoutes.DASHBOARD);
        container.add(employeePage, AppRoutes.EMPLOYEE_MANAGEMENT);

        add(container);

        cardLayout.show(container, AppRoutes.LOGIN);
        new LoginController(loginPanel, this);
        pack();
    }

    public void goTo(String route) {
        cardLayout.show(container, route);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}