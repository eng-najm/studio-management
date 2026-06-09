package com.studio.features.order.controller;

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
import com.studio.features.order.OrderDAO;
import com.studio.core.ComboItemsProvider;
import com.studio.features.order.model.ImageSessionDetailModel;
import com.studio.features.order.model.LaserDetailModel;
import com.studio.features.order.model.OrderModel;
import com.studio.features.order.model.PrintDetailModel;
import com.studio.features.order.view.EditOrderPage;
import com.studio.features.order.view.OrderDetailPage;
import com.studio.features.order.view.OrderPage;
import com.studio.features.order.view.OrderTable;

public class OrderController {
    private OrderPage orderPage;
    private OrderDetailPage orderDetailPage;
    private EditOrderPage editOrderPage;
    DashboardPage route;
    private OrderDAO orderDAO;
    private ComboItemsProvider comboProvider;

    private OrderModel currentOrder;
    private Object currentDetail;

    public OrderController(OrderPage orderPage, OrderDetailPage orderDetailPage,
            EditOrderPage editOrderPage, DashboardPage route) {
        this.orderPage = orderPage;
        this.orderDetailPage = orderDetailPage;
        this.editOrderPage = editOrderPage;
        this.route = route;
        orderDAO = new OrderDAO();
        comboProvider = new ComboItemsProvider();
        init();
    }

    void init() {
        fetchOrders();

        orderPage.getCreateButton().addActionListener(e -> {
            editOrderPage.setCustomerComboItems(comboProvider.getCustomerItems());
            editOrderPage.setReceptionistComboItems(comboProvider.getReceptionistItems());
            editOrderPage.setPhotographerComboItems(comboProvider.getPhotographerItems());
            editOrderPage.setCouponComboItems(comboProvider.getCouponItems());
            route.goTo(AppRoutes.EDIT_ORDER);
            editOrderPage.setAdd(true);
        });

        orderPage.getRefreshButton().addActionListener(e -> fetchOrders());
        orderDetailPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.ORDER_MANAGEMENT));
        editOrderPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.ORDER_MANAGEMENT));

        editOrderPage.getApplyChangeButton().addActionListener(e -> editOrder());
        editOrderPage.getAddButton().addActionListener(e -> addOrder());
        editOrderPage.getDeleteButton().addActionListener(e -> deleteOrder());

        addOrderListClickListener();
        orderDetailPage.addBaseTableClickListener(e -> openEditFromDetail());
        orderDetailPage.addTypeTableClickListener(e -> openEditFromDetail());
    }

    void fetchOrders() {
        Either<ArrayList<OrderModel>, Exception> result = orderDAO.getOrders();
        if (result.isLeft()) {
            orderPage.populateOrderList(result.getLeft());
        } else {
            orderPage.populateOrderList(new ArrayList<>());
            System.err.println("Failed to fetch orders: " + result.getRight().getMessage());
        }
    }

    void addOrderListClickListener() {
        JTable table = orderPage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    OrderTable model = (OrderTable) table.getModel();
                    OrderModel order = model.getOrderAt(row);

                    populateDetailPage(order);
                    route.goTo(AppRoutes.ORDER_DETAIL);
                }
            }
        });
    }

    void populateDetailPage(OrderModel order) {
        currentOrder = order;
        orderDetailPage.setOrderData(order);

        Either<?, Exception> detailResult = orderDAO.getTypeSpecificDetail(order.getId(), order.getOrderType());
        if (detailResult.isLeft()) {
            Object detail = detailResult.getLeft();
            currentDetail = detail;
            if (detail instanceof LaserDetailModel) {
                LaserDetailModel l = (LaserDetailModel) detail;
                orderDetailPage.setLaserDetail(l.getMaterials(), l.getDesignFileUrl());
            } else if (detail instanceof PrintDetailModel) {
                PrintDetailModel p = (PrintDetailModel) detail;
                orderDetailPage.setPrintDetail(p.getPrintType(), p.getQty(),
                        p.getPaperSize(), p.getPaperType(), p.getFilePath());
            } else if (detail instanceof ImageSessionDetailModel) {
                ImageSessionDetailModel im = (ImageSessionDetailModel) detail;
                orderDetailPage.setImageDetail(im.getSessionType(), im.getScheduledAt(),
                        im.getDuration(), im.getPhotographerName());
            }
        }
    }

    void openEditFromDetail() {
        if (currentOrder == null)
            return;
        editOrderPage.setCustomerComboItems(comboProvider.getCustomerItems());
        editOrderPage.setReceptionistComboItems(comboProvider.getReceptionistItems());
        editOrderPage.setPhotographerComboItems(comboProvider.getPhotographerItems());
        editOrderPage.setCouponComboItems(comboProvider.getCouponItems());
        editOrderPage.setOrderData(currentOrder, currentDetail);
        editOrderPage.setAdd(false);
        route.goTo(AppRoutes.EDIT_ORDER);
    }

    void addOrder() {
        List<String> errors = editOrderPage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(orderPage, errors);
            return;
        }
        OrderModel order = editOrderPage.getCurrentData();
        int orderId = orderDAO.addOrder(order);
        if (orderId > 0) {
            addTypeDetail(orderId);
            JOptionPane.showMessageDialog(orderPage, "Successfully added order");
            route.goTo(AppRoutes.ORDER_MANAGEMENT);
            fetchOrders();
        } else {
            JOptionPane.showMessageDialog(orderPage, "Failed to add order");
        }
    }

    void addTypeDetail(int orderId) {
        Object detail = editOrderPage.getCurrentTypeSpecific();
        if (detail instanceof LaserDetailModel) {
            orderDAO.addLaserDetail(orderId, (LaserDetailModel) detail);
        } else if (detail instanceof PrintDetailModel) {
            orderDAO.addPrintDetail(orderId, (PrintDetailModel) detail);
        } else if (detail instanceof ImageSessionDetailModel) {
            orderDAO.addImageSessionDetail(orderId, (ImageSessionDetailModel) detail);
        }
    }

    void editOrder() {
        List<String> errors = editOrderPage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(orderPage, errors);
            return;
        }
        OrderModel order = editOrderPage.getCurrentData();
        boolean result = orderDAO.updateOrder(order);
        if (result) {
            updateTypeDetail(order.getId());
            JOptionPane.showMessageDialog(orderPage, "Successfully updated order");
            route.goTo(AppRoutes.ORDER_MANAGEMENT);
            fetchOrders();
        } else {
            JOptionPane.showMessageDialog(orderPage, "Failed to update order");
        }
    }

    void updateTypeDetail(int orderId) {
        Object detail = editOrderPage.getCurrentTypeSpecific();
        if (detail instanceof LaserDetailModel) {
            LaserDetailModel l = (LaserDetailModel) detail;
            l.setOrderId(orderId);
            orderDAO.updateLaserDetail(l);
        } else if (detail instanceof PrintDetailModel) {
            PrintDetailModel p = (PrintDetailModel) detail;
            p.setOrderId(orderId);
            orderDAO.updatePrintDetail(p);
        } else if (detail instanceof ImageSessionDetailModel) {
            ImageSessionDetailModel im = (ImageSessionDetailModel) detail;
            im.setOrderId(orderId);
            orderDAO.updateImageSessionDetail(im);
        }
    }

    void deleteOrder() {
        int id = editOrderPage.getCurrentData().getId();
        if (id == 0)
            return;
        int confirm = JOptionPane.showConfirmDialog(editOrderPage,
                "Are you sure you want to delete this order?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;
        boolean result = orderDAO.deleteOrder(id);
        if (result) {
            JOptionPane.showMessageDialog(orderPage, "Successfully deleted order");
            route.goTo(AppRoutes.ORDER_MANAGEMENT);
            fetchOrders();
        } else {
            JOptionPane.showMessageDialog(orderPage, "Failed to delete order");
        }
    }
}
