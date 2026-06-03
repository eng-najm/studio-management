package com.studio.features.employee_management.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.studio.core.shared_widgets.AppLable;
import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeePage extends JPanel {
    EmployeeTable tableModel;

    JTable table = new JTable();

    public EmployeePage() {
        this.setLayout(new BorderLayout());
        JPanel taplePanel = new JPanel(new BorderLayout());

        tableModel = new EmployeeTable(new ArrayList<>());
        table.setModel(tableModel);

        table.setFont(new Font("", Font.PLAIN, 20));
        table.setRowHeight(35);

        TableButtonRendererEditor buttonRendererEditor = new TableButtonRendererEditor(new JCheckBox(), tableModel,
                this);
        table.getColumnModel().getColumn(0).setCellRenderer(buttonRendererEditor);
        table.getColumnModel().getColumn(0).setCellEditor(buttonRendererEditor);
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
        TableButtonRendererEditor buttonRendererEditor = new TableButtonRendererEditor(new JCheckBox(), tableModel,
                this);
        table.getColumnModel().getColumn(0).setCellRenderer(buttonRendererEditor);
        table.getColumnModel().getColumn(0).setCellEditor(buttonRendererEditor);

        table.revalidate();
        table.repaint();
    }

}