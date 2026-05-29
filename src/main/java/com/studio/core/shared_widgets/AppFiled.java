package com.studio.core.shared_widgets;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

public class AppFiled extends JTextField {

    public AppFiled() {
        Dimension d = new Dimension(400, 45);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    }
}