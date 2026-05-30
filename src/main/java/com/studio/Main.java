package com.studio;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.studio.Main;
import com.studio.core.constants.AppRoutes;
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

        container.add(loginPanel, AppRoutes.LOGIN);

        add(container);

        cardLayout.show(container, AppRoutes.LOGIN);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}