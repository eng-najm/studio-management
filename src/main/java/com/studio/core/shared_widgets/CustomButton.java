package com.studio.core.shared_widgets;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        this.setText(text);

        Dimension d = new Dimension(400, 45);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    }
}
