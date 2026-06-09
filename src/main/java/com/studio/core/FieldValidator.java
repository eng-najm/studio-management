package com.studio.core;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.JComboBox;

import com.studio.core.constants.ValidationType;
import com.studio.core.model.ComboItem;

public class FieldValidator {

    public static String validate(String fieldName, String value, ValidationType type) {
        if (type == null || type == ValidationType.NONE) {
            return null;
        }

        boolean empty = value == null || value.trim().isEmpty();

        if (type == ValidationType.REQUIRED) {
            if (empty) {
                return fieldName + " is required";
            }
            return null;
        }

        if (empty) {
            return null;
        }

        switch (type) {
            case INTEGER:
                try {
                    Integer.parseInt(value.trim());
                } catch (NumberFormatException e) {
                    return fieldName + " must be a valid integer";
                }
                break;
            case DECIMAL:
                try {
                    Double.parseDouble(value.trim());
                } catch (NumberFormatException e) {
                    return fieldName + " must be a valid number";
                }
                break;
            case TIMESTAMP:
                try {
                    Timestamp.valueOf(value.trim());
                } catch (IllegalArgumentException e) {
                    return fieldName + " must be a valid timestamp (yyyy-mm-dd hh:mm:ss)";
                }
                break;
            case SINGLE_CHAR:
                if (value.trim().length() != 1) {
                    return fieldName + " must be a single character";
                }
                break;
            default:
                break;
        }

        return null;
    }

    public static String validateComboRequired(String fieldName, JComboBox<ComboItem<?>> combo) {
        ComboItem<?> item = (ComboItem<?>) combo.getSelectedItem();
        if (item == null || item.getValue() == null) {
            return fieldName + " is required";
        }
        return null;
    }

    public static String validateStringComboRequired(String fieldName, JComboBox<String> combo) {
        Object item = combo.getSelectedItem();
        if (item == null || item.toString().trim().isEmpty()) {
            return fieldName + " is required";
        }
        return null;
    }

    public static void showErrors(java.awt.Component parent, List<String> errors) {
        String message = String.join("\n", errors);
        javax.swing.JOptionPane.showMessageDialog(parent, message, "Validation Errors",
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
