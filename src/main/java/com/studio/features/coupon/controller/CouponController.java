package com.studio.features.coupon.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.studio.core.Either;
import com.studio.core.constants.AppRoutes;
import com.studio.features.coupon.CouponDAO;
import com.studio.features.coupon.model.CouponModel;
import com.studio.features.coupon.view.CouponPage;
import com.studio.features.coupon.view.CouponTable;
import com.studio.features.coupon.view.EditCouponPage;
import com.studio.features.dashboard.view.DashboardPage;

public class CouponController {
    private CouponPage couponPage;
    private EditCouponPage editCouponPage;
    DashboardPage route;
    private CouponDAO couponDAO;

    public CouponController(CouponPage couponPage, EditCouponPage editCouponPage, DashboardPage route) {
        this.couponPage = couponPage;
        this.editCouponPage = editCouponPage;
        this.route = route;
        couponDAO = new CouponDAO();
        init();
    }

    void init() {
        fetchCoupons();
        editCouponPage.getApplyChangeButton().addActionListener(e -> editCoupon());
        editCouponPage.getAddButton().addActionListener(e -> addCoupon());
        editCouponPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.COUPON_MANAGEMENT));
        couponPage.getCreateButton().addActionListener(e -> {
            route.goTo(AppRoutes.EDIT_COUPON);
            editCouponPage.setAdd(true);
        });
        couponPage.getRefreshButton().addActionListener(e -> fetchCoupons());
        editCouponPage.getDeleteButton().addActionListener(e -> deleteCoupon());
        addTableRowClickListener();
    }

    void fetchCoupons() {
        Either<ArrayList<CouponModel>, Exception> result = couponDAO.getCoupons();
        if (result.isLeft()) {
            couponPage.populateCouponList(result.getLeft());
        } else {
            couponPage.populateCouponList(new ArrayList<>());
            System.err.println("Failed to fetch coupons: " + result.getRight().getMessage());
        }
    }

    void editCoupon() {
        boolean result = couponDAO.updateCoupon(editCouponPage.getCurrentData());
        if (result) {
            JOptionPane.showMessageDialog(couponPage, "Successfully updated coupon");
        } else {
            JOptionPane.showMessageDialog(couponPage, "Failed to update coupon");
        }
    }

    void addCoupon() {
        int row = couponDAO.addCoupon(editCouponPage.getCurrentData());
        if (row > 0) {
            JOptionPane.showMessageDialog(couponPage, "Successfully added coupon");
        } else {
            JOptionPane.showMessageDialog(couponPage, "Failed to add coupon");
        }
    }

    void deleteCoupon() {
        int id = editCouponPage.getCurrentData().getId();
        if (id == 0) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(couponPage,
                "Are you sure you want to delete this coupon?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        boolean result = couponDAO.deleteCoupon(id);
        if (result) {
            JOptionPane.showMessageDialog(couponPage, "Successfully deleted coupon");
            route.goTo(AppRoutes.COUPON_MANAGEMENT);
        } else {
            JOptionPane.showMessageDialog(couponPage, "Failed to delete coupon");
        }
    }

    private void addTableRowClickListener() {
        JTable table = couponPage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    CouponTable model = (CouponTable) table.getModel();
                    editCouponPage.setCouponData(model.getCouponAt(row));
                    route.goTo(AppRoutes.EDIT_COUPON);
                    editCouponPage.setAdd(false);
                }
            }
        });
    }
}
