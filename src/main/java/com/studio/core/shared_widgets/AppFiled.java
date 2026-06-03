package com.studio.core.shared_widgets;

import java.awt.Dimension;

import javax.swing.JTextField;

public class AppFiled extends JTextField {

    public AppFiled() {
        Dimension d = new Dimension(400, 45);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
    }
}