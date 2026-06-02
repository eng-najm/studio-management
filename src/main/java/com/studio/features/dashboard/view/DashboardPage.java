package com.studio.features.dashboard.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.studio.core.constants.AppRoutes;
import com.studio.core.shared_widgets.AppButton;
import com.studio.features.dashboard.model.DashItem;
import com.studio.features.employee_management.controller.EmployeeController;
import com.studio.features.employee_management.view.EmployeePage;

public class DashboardPage extends JPanel {
    DashItem[] dashItems = {
            new DashItem("Employee Management", "", AppRoutes.EMPLOYEE_MANAGEMENT),
            new DashItem("Customer Management", "", ""),
            new DashItem("Order Management", "", ""),
    };
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    EmployeePage employeePage = new EmployeePage();

    public DashboardPage() {
        new EmployeeController(employeePage);
        this.setLayout(new BorderLayout());
        //
        JPanel items = new JPanel();
        items.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        items.setSize(150, HEIGHT);
        for (DashItem item : dashItems) {
            AppButton button = new AppButton(item.getName());
            button.addActionListener(e -> goTo(item.getRoute()));
            items.add(button);
            items.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        //
        this.add(items, BorderLayout.WEST);
        this.add(container, BorderLayout.CENTER);
        container.add(employeePage, AppRoutes.EMPLOYEE_MANAGEMENT);

    }

    public void goTo(String route) {
        cardLayout.show(container, route);
    }
}
