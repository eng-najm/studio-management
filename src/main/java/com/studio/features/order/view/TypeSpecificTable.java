package com.studio.features.order.view;

import javax.swing.table.AbstractTableModel;

public class TypeSpecificTable extends AbstractTableModel {
    private String[] columnNames = {};
    private Object[] rowData = {};

    public void setLaserData(String materials, String designFileUrl) {
        columnNames = new String[]{ "Materials", "Design File URL" };
        rowData = new Object[]{ materials, designFileUrl };
        fireTableStructureChanged();
    }

    public void setPrintData(String printType, int qty, String paperSize, String paperType, String filePath) {
        columnNames = new String[]{ "Print Type", "Qty", "Paper Size", "Paper Type", "File Path" };
        rowData = new Object[]{ printType, qty, paperSize, paperType, filePath };
        fireTableStructureChanged();
    }

    public void setImageData(String sessionType, Object scheduledAt, int duration, String photographerName) {
        columnNames = new String[]{ "Session Type", "Scheduled At", "Duration (min)", "Photographer" };
        rowData = new Object[]{ sessionType, scheduledAt, duration, photographerName };
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return rowData.length > 0 ? 1 : 0;
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
        return rowData[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
