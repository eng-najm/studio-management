package com.studio.features.order.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.order.model.OrderModel;

public class OrderDetailPage extends JPanel {

    private OrderDetailTable orderDetailTable;
    private TypeSpecificTable typeSpecificTable;
    private JTable baseTable;
    private JTable typeTable;
    private AppButton backButton;

    public OrderDetailPage() {
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        AppLable titleLabel = new AppLable("Order Details");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        AppLable baseLabel = new AppLable("Order Information");
        baseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(baseLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        orderDetailTable = new OrderDetailTable();
        baseTable = new JTable(orderDetailTable);
        baseTable.setFont(new Font("", Font.PLAIN, 16));
        baseTable.setRowHeight(30);
        baseTable.setFillsViewportHeight(true);
        baseTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane baseScroll = new JScrollPane(baseTable);
        baseTable.setPreferredScrollableViewportSize(new Dimension(900, 70));
        baseScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        contentPanel.add(baseScroll);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        AppLable typeLabel = new AppLable("Type Details");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(typeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        typeSpecificTable = new TypeSpecificTable();
        typeTable = new JTable(typeSpecificTable);
        typeTable.setFont(new Font("", Font.PLAIN, 16));
        typeTable.setRowHeight(30);
        typeTable.setFillsViewportHeight(true);
        typeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane typeScroll = new JScrollPane(typeTable);
        typeTable.setPreferredScrollableViewportSize(new Dimension(700, 70));
        typeScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        contentPanel.add(typeScroll);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        backButton = new AppButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setOrderData(OrderModel order) {
        orderDetailTable.setOrderData(order);
    }

    public void setLaserDetail(String materials, String designFileUrl) {
        typeSpecificTable.setLaserData(materials, designFileUrl);
    }

    public void setPrintDetail(String printType, int qty, String paperSize, String paperType, String filePath) {
        typeSpecificTable.setPrintData(printType, qty, paperSize, paperType, filePath);
    }

    public void setImageDetail(String sessionType, Object scheduledAt, int duration, String photographerName) {
        typeSpecificTable.setImageData(sessionType, scheduledAt, duration, photographerName);
    }

    public void addBaseTableClickListener(ActionListener listener) {
        baseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public void addTypeTableClickListener(ActionListener listener) {
        typeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public AppButton getBackButton() {
        return backButton;
    }
}
