package com.studio.features.invoice.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.util.List;

import com.studio.core.Either;
import com.studio.core.FieldValidator;
import com.studio.core.constants.AppRoutes;
import com.studio.features.dashboard.view.DashboardPage;
import com.studio.features.invoice.InvoiceDAO;
import com.studio.features.invoice.model.InvoiceModel;
import com.studio.features.invoice.view.EditInvoicePage;
import com.studio.features.invoice.view.InvoicePage;
import com.studio.features.invoice.view.InvoiceTable;

public class InvoiceController {
    private InvoicePage invoicePage;
    private EditInvoicePage editInvoicePage;
    DashboardPage route;
    private InvoiceDAO invoiceDAO;

    public InvoiceController(InvoicePage invoicePage, EditInvoicePage editInvoicePage, DashboardPage route) {
        this.invoicePage = invoicePage;
        this.editInvoicePage = editInvoicePage;
        this.route = route;
        invoiceDAO = new InvoiceDAO();
        init();
    }

    void init() {
        fetchInvoices();

        invoicePage.getRefreshButton().addActionListener(e -> fetchInvoices());

        editInvoicePage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.INVOICE_MANAGEMENT));
        editInvoicePage.getApplyChangeButton().addActionListener(e -> editInvoice());

        addTableClickListener();
    }

    void fetchInvoices() {
        Either<ArrayList<InvoiceModel>, Exception> result = invoiceDAO.getInvoices();
        if (result.isLeft()) {
            invoicePage.populateInvoiceList(result.getLeft());
        } else {
            invoicePage.populateInvoiceList(new ArrayList<>());
            System.err.println("Failed to fetch invoices: " + result.getRight().getMessage());
        }
    }

    void editInvoice() {
        List<String> errors = editInvoicePage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(invoicePage, errors);
            return;
        }
        InvoiceModel invoice = editInvoicePage.getCurrentData();
        boolean result = invoiceDAO.updateInvoice(invoice);
        if (result) {
            JOptionPane.showMessageDialog(invoicePage, "Successfully updated invoice");
            route.goTo(AppRoutes.INVOICE_MANAGEMENT);
            fetchInvoices();
        } else {
            JOptionPane.showMessageDialog(invoicePage, "Failed to update invoice");
        }
    }

   

    void addTableClickListener() {
        JTable table = invoicePage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    InvoiceTable model = (InvoiceTable) table.getModel();
                    InvoiceModel invoice = model.getInvoiceAt(row);
                    editInvoicePage.setInvoiceData(invoice);
                    route.goTo(AppRoutes.EDIT_INVOICE);
                }
            }
        });
    }
}
