package com.studio.features.employee_management.view;

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
import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeePage extends JPanel {
    EmployeeTable tableModel;
    JTable table;
    private JButton createButton;

    public EmployeePage() {

        this.setLayout(new BorderLayout());
        JPanel taplePanel = new JPanel(new BorderLayout());

        tableModel = new EmployeeTable(new ArrayList<>());
        table = new JTable(tableModel);

        table.setFont(new Font("", Font.PLAIN, 20));
        table.setRowHeight(35);

        JScrollPane scrollPane = new JScrollPane(table);
        taplePanel.add(scrollPane, BorderLayout.CENTER);
        taplePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(taplePanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // (اختياري) إضافة مسافة بادئة (Padding) حول حواف اللوحة لتبدو أجمل
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // 2. إنشاء زر "إنشاء"
        createButton = new JButton("Create");

        // 3. إضافة الزر إلى طرف اللوحة (الشرق/اليمين)
        topPanel.add(createButton, BorderLayout.EAST);
        topPanel.add(new AppLable("Employee Management"), BorderLayout.CENTER);

        taplePanel.add(topPanel, BorderLayout.NORTH);

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

    public EmployeeTable getTableModel() {
        return tableModel;
    }

    public JButton getCreateButton() {
        return createButton;
    }

}