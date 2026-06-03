package com.studio.features.employee_management.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeeTable extends AbstractTableModel {
    private final String[] columnNames = { "Action", "ID", "First Name", "Middle Name", "Last Name",
            "Sex", "Phone", "Address", "Role", "Salary", "Username", "Password" };
    private final List<EmployeeModel> employees;

    public EmployeeTable(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmployeeModel employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return "Edit";
            case 1:
                return employee.getId();
            case 2:
                return employee.getFirstName();
            case 3:
                return employee.getMeddilName();
            case 4:
                return employee.getLastName();
            case 5:
                return employee.getSex();
            case 6:
                return employee.getPhone();
            case 7:
                return employee.getAddress();
            case 8:
                return employee.getRole();
            case 9:
                return employee.getSalary() + " $";
            case 10:
                return employee.getUserName();
            case 11:
                return employee.getUserPassword();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    public EmployeeModel getEmployeeAt(int rowIndex) {
        return employees.get(rowIndex);
    }
}