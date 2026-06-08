package com.studio.features.customer.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.studio.core.shared_widgets.AppLable;
import com.studio.features.customer.model.CustomerModel;

public class CustomerPage extends JPanel {
    CustomerTable tableModel;
    JTable table;
    private JButton createButton;
    private JButton refreshButton;

    public CustomerPage() {
        this.setLayout(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());

        tableModel = new CustomerTable(new ArrayList<>());
        table = new JTable(tableModel);

        table.setFont(new Font("", Font.PLAIN, 20));
        table.setRowHeight(35);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(tablePanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        createButton = new JButton("Create");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel(new BorderLayout(5, 0));
        buttonPanel.add(refreshButton, BorderLayout.WEST);
        buttonPanel.add(createButton, BorderLayout.EAST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        topPanel.add(new AppLable("Customer Management"), BorderLayout.CENTER);

        tablePanel.add(topPanel, BorderLayout.NORTH);
    }

    public void populateCustomerList(List<CustomerModel> customers) {
        tableModel = new CustomerTable(customers);
        table.setModel(tableModel);
        table.revalidate();
        table.repaint();
    }

    public JTable getTable() {
        return table;
    }

    public CustomerTable getTableModel() {
        return tableModel;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}
