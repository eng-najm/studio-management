package com.studio.features.order.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;
import java.sql.Timestamp;

import com.studio.core.FieldValidator;
import com.studio.core.constants.ValidationType;
import com.studio.core.model.ComboItem;
import com.studio.core.shared_widgets.AppButton;
import com.studio.core.shared_widgets.AppFiled;
import com.studio.core.shared_widgets.AppLable;
import com.studio.features.order.model.ImageSessionDetailModel;
import com.studio.features.order.model.LaserDetailModel;
import com.studio.features.order.model.OrderModel;
import com.studio.features.order.model.PrintDetailModel;

public class EditOrderPage extends JPanel {
    // Hidden id
    AppFiled idField = new AppFiled();

    // Base fields
    JComboBox<ComboItem<?>> customerCombo = new JComboBox<>();
    JComboBox<ComboItem<?>> receptionistCombo = new JComboBox<>();
    JComboBox<ComboItem<?>> couponCombo = new JComboBox<>();
    AppFiled discountPercentField = new AppFiled();
    AppFiled startAtField = new AppFiled();
    AppFiled endAtField = new AppFiled();
    JComboBox<String> statusCombo = new JComboBox<>(
            new String[] { "0 - Pending", "1 - Active", "2 - Canceled", "3 - Complete" });
    AppFiled priceField = new AppFiled();
    AppFiled descriptionField = new AppFiled();
    JComboBox<String> orderTypeCombo = new JComboBox<>(new String[] { "", "LASER", "PRINT", "IMAGE" });

    // Laser panel fields
    JPanel laserPanel;
    AppFiled materialsField = new AppFiled();
    AppFiled designFileUrlField = new AppFiled();

    // Print panel fields
    JPanel printPanel;
    AppFiled printTypeField = new AppFiled();
    AppFiled qtyField = new AppFiled();
    AppFiled paperSizeField = new AppFiled();
    AppFiled paperTypeField = new AppFiled();
    AppFiled filePathField = new AppFiled();

    // Image panel fields
    JPanel imagePanel;
    AppFiled sessionTypeField = new AppFiled();
    AppFiled scheduledAtField = new AppFiled();
    AppFiled durationField = new AppFiled();
    JComboBox<ComboItem<?>> photographerCombo = new JComboBox<>();

    private AppButton applyChangeButton;
    private AppButton addButton;
    private AppButton deleteButton;
    private AppButton backButton;

    private JPanel formPanel;
    private JPanel mainContainer;

    public EditOrderPage() {
        this.setLayout(new BorderLayout());

        backButton = new AppButton("Back");
        applyChangeButton = new AppButton("Apply Changes");
        addButton = new AppButton("Add");
        deleteButton = new AppButton("Delete");

        mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Base fields
        addComboField(formPanel, "Customer", customerCombo);
        addComboField(formPanel, "Receptionist", receptionistCombo);
        addComboField(formPanel, "Coupon (optional)", couponCombo);
        addField(formPanel, "Discount Percent", discountPercentField);
        addField(formPanel, "Start At (yyyy-mm-dd hh:mm:ss)", startAtField);
        addField(formPanel, "End At (yyyy-mm-dd hh:mm:ss)", endAtField);
        addComboField(formPanel, "Status", statusCombo);
        addField(formPanel, "Price", priceField);
        addField(formPanel, "Description", descriptionField);
        addComboField(formPanel, "Order Type", orderTypeCombo);

        // Laser panel
        laserPanel = new JPanel();
        laserPanel.setLayout(new BoxLayout(laserPanel, BoxLayout.Y_AXIS));
        laserPanel.setBorder(BorderFactory.createTitledBorder("Laser Details"));
        addField(laserPanel, "Materials", materialsField);
        addField(laserPanel, "Design File URL", designFileUrlField);
        laserPanel.setVisible(false);

        // Print panel
        printPanel = new JPanel();
        printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));
        printPanel.setBorder(BorderFactory.createTitledBorder("Print Details"));
        addField(printPanel, "Print Type", printTypeField);
        addField(printPanel, "Quantity", qtyField);
        addField(printPanel, "Paper Size", paperSizeField);
        addField(printPanel, "Paper Type", paperTypeField);
        addField(printPanel, "File Path", filePathField);
        printPanel.setVisible(false);

        // Image panel
        imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setBorder(BorderFactory.createTitledBorder("Image Session Details"));
        addField(imagePanel, "Session Type", sessionTypeField);
        addField(imagePanel, "Scheduled At (yyyy-mm-dd hh:mm:ss)", scheduledAtField);
        addField(imagePanel, "Duration (minutes)", durationField);
        addComboField(imagePanel, "Photographer", photographerCombo);
        imagePanel.setVisible(false);

        formPanel.add(laserPanel);
        formPanel.add(printPanel);
        formPanel.add(imagePanel);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(applyChangeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Wire type combo to show/hide panels
        orderTypeCombo.addActionListener(e -> updateTypePanels());
    }

    private void updateTypePanels() {
        String type = (String) orderTypeCombo.getSelectedItem();
        laserPanel.setVisible("LASER".equals(type));
        printPanel.setVisible("PRINT".equals(type));
        imagePanel.setVisible("IMAGE".equals(type));
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void addField(JPanel panel, String labelText, AppFiled field) {
        AppLable label = new AppLable(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void addComboField(JPanel panel, String labelText, JComboBox<?> combo) {
        AppLable label = new AppLable(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        combo.setPreferredSize(new Dimension(400, 45));
        combo.setMinimumSize(new Dimension(400, 45));
        combo.setMaximumSize(new Dimension(400, 45));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(combo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void setCustomerComboItems(List<ComboItem<Integer>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        model.addAll(items);
        customerCombo.setModel(model);
    }

    public void setReceptionistComboItems(List<ComboItem<Integer>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        model.addAll(items);
        receptionistCombo.setModel(model);
    }

    public void setCouponComboItems(List<ComboItem<Integer>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        ComboItem<Integer> none = new ComboItem<>(null, "(None)");
        model.addElement(none);
        model.addAll(items);
        couponCombo.setModel(model);
    }

    public void setPhotographerComboItems(List<ComboItem<Integer>> items) {
        DefaultComboBoxModel<ComboItem<?>> model = new DefaultComboBoxModel<>();
        model.addAll(items);
        photographerCombo.setModel(model);
    }

    private int getSelectedId(JComboBox<ComboItem<?>> combo) {
        ComboItem<?> item = (ComboItem<?>) combo.getSelectedItem();
        if (item != null && item.getValue() != null) {
            return (int) item.getValue();
        }
        return 0;
    }

    private void selectById(JComboBox<ComboItem<?>> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ComboItem<?> item = combo.getItemAt(i);
            if (item.getValue() != null && (int) item.getValue() == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    public void setOrderData(OrderModel order, Object detail) {
        idField.setText(String.valueOf(order.getId()));
        selectById(customerCombo, order.getCustomerId());
        selectById(receptionistCombo, order.getReceptionistId());
        if (order.getCouponId() != null) {
            selectById(couponCombo, order.getCouponId());
        }
        discountPercentField.setText(String.valueOf(order.getDiscountPercent()));
        startAtField.setText(String.valueOf(order.getStartAt()));
        endAtField.setText(String.valueOf(order.getEndAt()));
        statusCombo.setSelectedIndex(order.getStatus());
        priceField.setText(String.valueOf(order.getPrice()));
        descriptionField.setText(order.getDescription());
        orderTypeCombo.setSelectedItem(order.getOrderType());
        orderTypeCombo.setEnabled(false);

        if (detail instanceof LaserDetailModel) {
            LaserDetailModel l = (LaserDetailModel) detail;
            materialsField.setText(l.getMaterials());
            designFileUrlField.setText(l.getDesignFileUrl());
        } else if (detail instanceof PrintDetailModel) {
            PrintDetailModel p = (PrintDetailModel) detail;
            printTypeField.setText(p.getPrintType());
            qtyField.setText(String.valueOf(p.getQty()));
            paperSizeField.setText(p.getPaperSize());
            paperTypeField.setText(p.getPaperType());
            filePathField.setText(p.getFilePath());
        } else if (detail instanceof ImageSessionDetailModel) {
            ImageSessionDetailModel im = (ImageSessionDetailModel) detail;
            sessionTypeField.setText(im.getSessionType());
            scheduledAtField.setText(String.valueOf(im.getScheduledAt()));
            durationField.setText(String.valueOf(im.getDuration()));
            selectById(photographerCombo, im.getPhotographerId());
        }
    }

    public OrderModel getCurrentData() {
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
        int customerId = getSelectedId(customerCombo);
        int receptionistId = getSelectedId(receptionistCombo);
        ComboItem<?> couponItem = (ComboItem<?>) couponCombo.getSelectedItem();
        Integer couponId = (couponItem != null && couponItem.getValue() != null)
                ? (int) couponItem.getValue()
                : null;
        double discountPercent = discountPercentField.getText().isEmpty() ? 1.0
                : Double.parseDouble(discountPercentField.getText());
        Timestamp startAt = startAtField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(startAtField.getText());
        Timestamp endAt = endAtField.getText().isEmpty() ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(endAtField.getText());
        int status = statusCombo.getSelectedIndex();
        int price = priceField.getText().isEmpty() ? 0 : Integer.parseInt(priceField.getText());
        String description = descriptionField.getText();
        String orderType = (String) orderTypeCombo.getSelectedItem();

        return new OrderModel(id, customerId, null, receptionistId, null, couponId,
                discountPercent, startAt, endAt, null, status, price, description, orderType);
    }

    public Object getCurrentTypeSpecific() {
        String type = (String) orderTypeCombo.getSelectedItem();
        switch (type) {
            case "LASER":
                return new LaserDetailModel(0,
                        materialsField.getText(),
                        designFileUrlField.getText());
            case "PRINT":
                return new PrintDetailModel(0,
                        printTypeField.getText(),
                        qtyField.getText().isEmpty() ? 0 : Integer.parseInt(qtyField.getText()),
                        paperSizeField.getText(),
                        paperTypeField.getText(),
                        filePathField.getText());
            case "IMAGE":
                int photoId = getSelectedId(photographerCombo);
                Timestamp schedAt = scheduledAtField.getText().isEmpty()
                        ? new Timestamp(System.currentTimeMillis())
                        : Timestamp.valueOf(scheduledAtField.getText());
                return new ImageSessionDetailModel(0,
                        sessionTypeField.getText(),
                        schedAt,
                        durationField.getText().isEmpty() ? 0 : Integer.parseInt(durationField.getText()),
                        photoId, null);
            default:
                return null;
        }
    }

    public AppButton getApplyChangeButton() {
        return applyChangeButton;
    }

    public AppButton getBackButton() {
        return backButton;
    }

    public AppButton getAddButton() {
        return addButton;
    }

    public AppButton getDeleteButton() {
        return deleteButton;
    }

    public List<String> validateFields() {
        List<String> errors = new ArrayList<>();
        addError(errors, FieldValidator.validate("Discount Percent", discountPercentField.getText(), ValidationType.DECIMAL));
        addError(errors, FieldValidator.validate("Start At", startAtField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Start At", startAtField.getText(), ValidationType.TIMESTAMP));
        addError(errors, FieldValidator.validate("End At", endAtField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("End At", endAtField.getText(), ValidationType.TIMESTAMP));
        addError(errors, FieldValidator.validate("Price", priceField.getText(), ValidationType.REQUIRED));
        addError(errors, FieldValidator.validate("Price", priceField.getText(), ValidationType.INTEGER));
        addError(errors, FieldValidator.validateStringComboRequired("Order Type", orderTypeCombo));

        String type = (String) orderTypeCombo.getSelectedItem();
        if ("LASER".equals(type)) {
            addError(errors, FieldValidator.validate("Materials", materialsField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Design File URL", designFileUrlField.getText(), ValidationType.REQUIRED));
        } else if ("PRINT".equals(type)) {
            addError(errors, FieldValidator.validate("Print Type", printTypeField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Quantity", qtyField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Quantity", qtyField.getText(), ValidationType.INTEGER));
            addError(errors, FieldValidator.validate("Paper Size", paperSizeField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Paper Type", paperTypeField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("File Path", filePathField.getText(), ValidationType.REQUIRED));
        } else if ("IMAGE".equals(type)) {
            addError(errors, FieldValidator.validate("Session Type", sessionTypeField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Scheduled At", scheduledAtField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Scheduled At", scheduledAtField.getText(), ValidationType.TIMESTAMP));
            addError(errors, FieldValidator.validate("Duration", durationField.getText(), ValidationType.REQUIRED));
            addError(errors, FieldValidator.validate("Duration", durationField.getText(), ValidationType.INTEGER));
        }
        return errors;
    }

    private void addError(List<String> errors, String error) {
        if (error != null) {
            errors.add(error);
        }
    }

    public void setAdd(boolean isAdd) {
        if (isAdd) {
            idField.setText("");
            discountPercentField.setText("1.0");
            startAtField.setText("");
            endAtField.setText("");
            priceField.setText("");
            descriptionField.setText("");
            orderTypeCombo.setSelectedIndex(0);
            orderTypeCombo.setEnabled(true);
            materialsField.setText("");
            designFileUrlField.setText("");
            printTypeField.setText("");
            qtyField.setText("");
            paperSizeField.setText("");
            paperTypeField.setText("");
            filePathField.setText("");
            sessionTypeField.setText("");
            scheduledAtField.setText("");
            durationField.setText("");
        }
        deleteButton.setVisible(!isAdd);
        applyChangeButton.setVisible(!isAdd);
        addButton.setVisible(isAdd);
    }
}
