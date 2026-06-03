package com.studio.core.shared_widgets;

import java.awt.Dimension;

import javax.swing.JButton;

public class AppButton extends JButton {
    public AppButton(String text) {
        this.setText(text);

        Dimension d = new Dimension(400, 45);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
    }
}
