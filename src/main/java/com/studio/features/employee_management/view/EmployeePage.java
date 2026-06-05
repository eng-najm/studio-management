package com.studio.features.employee_management.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.studio.core.shared_widgets.AppLable;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeePage extends JPanel {
    EmployeeTable tableModel;

    public EmployeeTable getTableModel() {
        return tableModel;
    }

    JTable table;

    DashboardPage route;
    TableActionListener listener;

    public interface TableActionListener {
        void onButtonClick(int rowIndex);
    }

    public EmployeePage() {

        this.setLayout(new BorderLayout());
        JPanel taplePanel = new JPanel(new BorderLayout());

        tableModel = new EmployeeTable(new ArrayList<>());
        table = new JTable(tableModel);

        table.setFont(new Font("", Font.PLAIN, 20));
        table.setRowHeight(35);
        table.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(table);
        taplePanel.add(scrollPane, BorderLayout.CENTER);
        taplePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(taplePanel, BorderLayout.CENTER);

        this.add(new AppLable("Employee Management"), BorderLayout.NORTH);

    }

    public void populateEmployeeList(List<EmployeeModel> employees) {
        tableModel = new EmployeeTable(employees);
        table.setModel(tableModel);
        table.revalidate();
        table.repaint();
    }

    public JTable getTable() {
        return table;
    }

}