package com.studio.features.payment.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.studio.core.ComboItemsProvider;
import com.studio.core.Either;
import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.payment.PaymentDAO;
import com.studio.features.payment.model.PaymentModel;
import com.studio.features.payment.view.EditPaymentPage;
import com.studio.features.payment.view.PaymentPage;
import com.studio.features.payment.view.PaymentTable;

public class PaymentController {
    private PaymentPage paymentPage;
    private EditPaymentPage editPaymentPage;
    DashboardPage route;
    private PaymentDAO paymentDAO;
    private ComboItemsProvider comboProvider;

    public PaymentController(PaymentPage paymentPage, EditPaymentPage editPaymentPage, DashboardPage route) {
        this.paymentPage = paymentPage;
        this.editPaymentPage = editPaymentPage;
        this.route = route;
        paymentDAO = new PaymentDAO();
        comboProvider = new ComboItemsProvider();
        init();
    }

    void init() {
        fetchPayments();

        paymentPage.getRefreshButton().addActionListener(e -> fetchPayments());
        paymentPage.getCreateButton().addActionListener(e -> {
            editPaymentPage.setInvoiceComboItems(comboProvider.getInvoiceItems());
            editPaymentPage.setAdd(true);
            route.goTo(AppRoutes.EDIT_PAYMENT);
        });

        editPaymentPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.PAYMENT_MANAGEMENT));
        editPaymentPage.getApplyChangeButton().addActionListener(e -> editPayment());
        editPaymentPage.getAddButton().addActionListener(e -> addPayment());
        editPaymentPage.getDeleteButton().addActionListener(e -> deletePayment());

        addTableClickListener();
    }

    void fetchPayments() {
        Either<ArrayList<PaymentModel>, Exception> result = paymentDAO.getPayments();
        if (result.isLeft()) {
            paymentPage.populatePaymentList(result.getLeft());
        } else {
            paymentPage.populatePaymentList(new ArrayList<>());
            System.err.println("Failed to fetch payments: " + result.getRight().getMessage());
        }
    }

    void addPayment() {
        PaymentModel payment = editPaymentPage.getCurrentData();
        int result = paymentDAO.addPayment(payment);
        if (result > 0) {
            JOptionPane.showMessageDialog(paymentPage, "Payment added successfully");
            route.goTo(AppRoutes.PAYMENT_MANAGEMENT);
            fetchPayments();
        } else {
            JOptionPane.showMessageDialog(paymentPage, "Failed to add payment");
        }
    }

    void editPayment() {
        PaymentModel payment = editPaymentPage.getCurrentData();
        boolean result = paymentDAO.updatePayment(payment);
        if (result) {
            JOptionPane.showMessageDialog(paymentPage, "Payment updated successfully");
            route.goTo(AppRoutes.PAYMENT_MANAGEMENT);
            fetchPayments();
        } else {
            JOptionPane.showMessageDialog(paymentPage, "Failed to update payment");
        }
    }

    void deletePayment() {
        PaymentModel payment = editPaymentPage.getCurrentData();
        int confirm = JOptionPane.showConfirmDialog(paymentPage,
                "Are you sure you want to delete this payment?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = paymentDAO.deletePayment(payment.getPaymentNumber(), payment.getInvoiceId());
            if (result) {
                JOptionPane.showMessageDialog(paymentPage, "Payment deleted successfully");
                route.goTo(AppRoutes.PAYMENT_MANAGEMENT);
                fetchPayments();
            } else {
                JOptionPane.showMessageDialog(paymentPage, "Failed to delete payment");
            }
        }
    }

    void addTableClickListener() {
        JTable table = paymentPage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    PaymentTable model = (PaymentTable) table.getModel();
                    PaymentModel payment = model.getPaymentAt(row);
                    editPaymentPage.setInvoiceComboItems(comboProvider.getInvoiceItems());
                    editPaymentPage.setPaymentData(payment);
                    editPaymentPage.setAdd(false);
                    route.goTo(AppRoutes.EDIT_PAYMENT);
                }
            }
        });
    }
}
