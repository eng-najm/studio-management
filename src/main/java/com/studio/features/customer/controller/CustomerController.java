package com.studio.features.customer.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.studio.core.Either;
import com.studio.core.constants.AppRoutes;
import com.studio.features.customer.CustomerDAO;
import com.studio.features.customer.model.CustomerModel;
import com.studio.features.customer.view.CustomerPage;
import com.studio.features.customer.view.CustomerTable;
import com.studio.features.customer.view.EditCustomerPage;
import com.studio.features.dashboard.view.DashboardPage;

public class CustomerController {
    private CustomerPage customerPage;
    private EditCustomerPage editCustomerPage;
    DashboardPage route;
    private CustomerDAO customerDAO;

    public CustomerController(CustomerPage customerPage, EditCustomerPage editCustomerPage, DashboardPage route) {
        this.customerPage = customerPage;
        this.editCustomerPage = editCustomerPage;
        this.route = route;
        customerDAO = new CustomerDAO();
        init();
    }

    void init() {
        fetchCustomers();
        editCustomerPage.getApplyChangeButton().addActionListener(e -> editCustomer());
        customerPage.getRefreshButton().addActionListener(e -> fetchCustomers());
        addCreateCustomerButton();
        editCustomerPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.CUSTOMER_MANAGEMENT));
        addEditButtonListener();
        editCustomerPage.getAddButton().addActionListener(e -> addCustomer());
    }

    void addCreateCustomerButton() {
        customerPage.getCreateButton().addActionListener(e -> {
            route.goTo(AppRoutes.EDIT_CUSTOMER);
            editCustomerPage.setAdd(true);
        });
    }

    public void fetchCustomers() {
        Either<ArrayList<CustomerModel>, Exception> result = customerDAO.getCustomers();
        if (result.isLeft()) {
            customerPage.populateCustomerList(result.getLeft());
        } else {
            customerPage.populateCustomerList(new ArrayList<>());
            System.err.println("Failed to fetch customers: " + result.getRight().getMessage());
        }
    }

    public void editCustomer() {
        boolean result = customerDAO.updateCustomer(editCustomerPage.getCurrentData());
        if (result) {
            JOptionPane.showMessageDialog(customerPage, "Successful");
        } else {
            JOptionPane.showMessageDialog(customerPage, "Failed");
        }
    }

    public void addCustomer() {
        int row = customerDAO.addCustomer(editCustomerPage.getCurrentData());
        if (row > 0) {
            JOptionPane.showMessageDialog(customerPage, "Successful");
        } else {
            JOptionPane.showMessageDialog(customerPage, "Failed");
        }
    }

    private void addEditButtonListener() {
        JTable table = customerPage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    CustomerTable cust = (CustomerTable) table.getModel();
                    editCustomerPage.setCustomerData(cust.getCustomerAt(row));
                    route.goTo(AppRoutes.EDIT_CUSTOMER);
                    editCustomerPage.setAdd(false);
                }
            }
        });
    }
}
