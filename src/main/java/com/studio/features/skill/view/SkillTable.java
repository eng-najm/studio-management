package com.studio.features.skill.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.studio.features.skill.model.SkillModel;

public class SkillTable extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Name" };
    private final List<SkillModel> skills;

    public SkillTable(List<SkillModel> skills) {
        this.skills = skills;
    }

    @Override
    public int getRowCount() {
        return skills.size();
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
        SkillModel skill = skills.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return skill.getId();
            case 1:
                return skill.getName();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public SkillModel getSkillAt(int rowIndex) {
        return skills.get(rowIndex);
    }
}
