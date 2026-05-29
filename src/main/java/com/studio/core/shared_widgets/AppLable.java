package com.studio.core.shared_widgets;

import java.awt.Font;

import javax.swing.JLabel;

public class AppLable extends JLabel {
    public AppLable(String text) {
        this.setText(text);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    }
}
