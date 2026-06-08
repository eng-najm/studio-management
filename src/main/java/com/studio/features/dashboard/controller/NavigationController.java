package com.studio.features.dashboard.controller;

import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;

public class NavigationController {
    DashboardPage dashboardPage;

    public NavigationController(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;

    }

    public void init() {
        dashboardPage.getEmpButton().addActionListener(e -> dashboardPage.goTo(AppRoutes.EMPLOYEE_MANAGEMENT));
        dashboardPage.getCustButton().addActionListener(e -> dashboardPage.goTo(AppRoutes.CUSTOMER_MANAGEMENT));
    }

}
