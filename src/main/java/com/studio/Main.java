package com.studio;

import java.awt.CardLayout;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.studio.Main;
import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.login.controller.LoginController;
import com.studio.features.login.view.LoginPage;

public class Main extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    public Main() {
        FlatLightLaf.setup();
        try {
            File fontFile = new File("src/main/java/com/studio/assets/fontBold.otf");
            Font newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(25f);
            UIManager.put("defaultFont", newFont);
        } catch (Exception e) {

        }

        setTitle("App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        LoginPage loginPanel = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();

        container.add(loginPanel, AppRoutes.LOGIN);
        container.add(dashboardPage, AppRoutes.DASHBOARD);

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