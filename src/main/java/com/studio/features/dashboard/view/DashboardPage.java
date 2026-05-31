package com.studio.features.dashboard.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.studio.core.constants.AppRoutes;
import com.studio.core.shared_widgets.AppButton;
import com.studio.features.dashboard.model.DashItem;

public class DashboardPage extends JPanel {
    DashItem[] dashItems = {
            new DashItem("Employee Management", "", AppRoutes.DASHBOARD),
            new DashItem("Customer Management", "", ""),
            new DashItem("Order Management", "", ""),
            new DashItem("LogOut", "", "")
    };

    public DashboardPage() {

        this.setLayout(new BorderLayout());
        JPanel items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        items.setSize(150, HEIGHT);
        for (DashItem item : dashItems) {
            items.add(new AppButton(item.getName()));
            items.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        this.add(items, BorderLayout.WEST);

    }

}
