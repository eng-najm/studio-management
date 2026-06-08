package com.studio.features.attendance.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.attendance.model.AttendanceModel;

public class AttendanceTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Emp ID", "Check In", "Check Out", "Date", "Status" };
    private final List<AttendanceModel> attendances;

    public AttendanceTable(List<AttendanceModel> attendances) {
        this.attendances = attendances;
    }

    @Override
    public int getRowCount() {
        return attendances.size();
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
        AttendanceModel a = attendances.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.getId();
            case 1: return a.getEmpId();
            case 2: return a.getCheckIn();
            case 3: return a.getCheckOut();
            case 4: return a.getDate();
            case 5: return statusLabel(a.getStatus());
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public AttendanceModel getAttendanceAt(int rowIndex) {
        return attendances.get(rowIndex);
    }

    private String statusLabel(int status) {
        switch (status) {
            case 0: return "Present";
            case 1: return "Absent";
            case 2: return "Late";
            case 3: return "Excused";
            case 4: return "Leave";
            default: return "Unknown";
        }
    }
}
