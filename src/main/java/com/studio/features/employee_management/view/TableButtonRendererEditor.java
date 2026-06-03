package com.studio.features.employee_management.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.studio.features.employee_management.model.EmployeeModel;

class TableButtonRendererEditor extends DefaultCellEditor implements TableCellRenderer {
    private final JButton button;
    private int clickedRow;

    public TableButtonRendererEditor(JCheckBox checkBox, EmployeeTable tableModel, Component parentComponent) {
        super(checkBox);
        button = new JButton("تعديل");
        button.setOpaque(true);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();

                EmployeeModel selectedEmployee = tableModel.getEmployeeAt(clickedRow);

                openEditDialog(parentComponent, selectedEmployee);
            }

        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.clickedRow = row;
        return button;
    }

    private void openEditDialog(Component parent, EmployeeModel employee) {
        JOptionPane.showMessageDialog(parent,
                "سيتم الانتقال لصفحة التعديل للموظف:\n" +
                        "الاسم: " + employee.getAddress() + "\n" +
                        "الرقم الوظيفي: #" + employee.getId(),
                "نافذة التعديل",
                JOptionPane.INFORMATION_MESSAGE);
    }
}