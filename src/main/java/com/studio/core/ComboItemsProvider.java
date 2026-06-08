package com.studio.core;

import java.util.ArrayList;
import java.util.List;

import com.studio.core.constants.EmployeeType;
import com.studio.core.model.ComboItem;
import com.studio.features.coupon.CouponDAO;
import com.studio.features.coupon.model.CouponModel;
import com.studio.features.customer.CustomerDAO;
import com.studio.features.customer.model.CustomerModel;
import com.studio.features.employee_management.EmployeeDAO;
import com.studio.features.employee_management.model.EmployeeModel;
import com.studio.features.order.OrderDAO;
import com.studio.features.order.model.OrderModel;

public class ComboItemsProvider {

    private final CustomerDAO customerDAO;
    private final EmployeeDAO employeeDAO;
    private final CouponDAO couponDAO;
    private final OrderDAO orderDAO;

    public ComboItemsProvider() {
        customerDAO = new CustomerDAO();
        employeeDAO = new EmployeeDAO();
        couponDAO = new CouponDAO();
        orderDAO = new OrderDAO();
    }

    public List<ComboItem<Integer>> getCustomerItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = customerDAO.getCustomers();
        if (result.isLeft()) {
            for (CustomerModel c : result.getLeft()) {
                items.add(new ComboItem<>(c.getId(),
                        c.getId() + " - " + c.getFirstName() + " " + c.getLastName()));
            }
        }
        return items;
    }

    public List<ComboItem<Integer>> getReceptionistItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = employeeDAO.getEmployeesByType(EmployeeType.RECEPTIONIST);
        if (result.isLeft()) {
            for (EmployeeModel emp : result.getLeft()) {
                items.add(new ComboItem<>(emp.getId(),
                        emp.getId() + " - " + emp.getFirstName() + " " + emp.getLastName()));
            }
        }
        return items;
    }

    public List<ComboItem<Integer>> getPhotographerItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = employeeDAO.getEmployeesByType(EmployeeType.PHOTOGRAPHER);
        if (result.isLeft()) {
            for (EmployeeModel emp : result.getLeft()) {
                items.add(new ComboItem<>(emp.getId(),
                        emp.getId() + " - " + emp.getFirstName() + " " + emp.getLastName()));
            }
        }
        return items;
    }

    public List<ComboItem<Integer>> getCouponItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = couponDAO.getCoupons();
        if (result.isLeft()) {
            for (CouponModel c : result.getLeft()) {
                if (c.getStatus() == 1) {
                    items.add(new ComboItem<>(c.getId(),
                            c.getId() + " - " + c.getCode() + " (" + c.getDiscountPercent() + "%)"));
                }
            }
        }
        return items;
    }

    public List<ComboItem<Integer>> getEmployeeItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = employeeDAO.getEmployees();
        if (result.isLeft()) {
            for (EmployeeModel emp : result.getLeft()) {
                items.add(new ComboItem<>(emp.getId(),
                        emp.getId() + " - " + emp.getFirstName() + " " + emp.getLastName()));
            }
        }
        return items;
    }

    public List<ComboItem<Integer>> getOrderItems() {
        List<ComboItem<Integer>> items = new ArrayList<>();
        var result = orderDAO.getOrders();
        if (result.isLeft()) {
            for (OrderModel o : result.getLeft()) {
                items.add(new ComboItem<>(o.getId(),
                        o.getId() + " - " + o.getCustomerName() + " (" + o.getOrderType() + ")"));
            }
        }
        return items;
    }
}
