package com.studio.features.dashboard.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.studio.core.constants.AppRoutes;
import com.studio.core.shared_widgets.AppButton;
import com.studio.features.dashboard.model.DashItem;
import com.studio.features.employee_management.controller.EmployeeController;
import com.studio.features.attendance.controller.AttendanceController;
import com.studio.features.attendance.view.AttendancePage;
import com.studio.features.attendance.view.EditAttendancePage;
import com.studio.features.invoice.controller.InvoiceController;
import com.studio.features.invoice.view.EditInvoicePage;
import com.studio.features.invoice.view.InvoicePage;
import com.studio.features.coupon.controller.CouponController;
import com.studio.features.coupon.view.CouponPage;
import com.studio.features.coupon.view.EditCouponPage;
import com.studio.features.customer.controller.CustomerController;
import com.studio.features.customer.view.CustomerPage;
import com.studio.features.customer.view.EditCustomerPage;
import com.studio.features.dashboard.controller.NavigationController;
import com.studio.features.employee_management.view.EditEmployeePage;
import com.studio.features.employee_management.view.EmployeePage;
import com.studio.features.order.controller.OrderController;
import com.studio.features.order.view.EditOrderPage;
import com.studio.features.order.view.OrderDetailPage;
import com.studio.features.order.view.OrderPage;
import com.studio.features.skill.controller.SkillController;
import com.studio.features.skill.view.EditSkillPage;
import com.studio.features.skill.view.SkillPage;

public class DashboardPage extends JPanel {
    DashItem[] dashItems = {
            new DashItem("Employee Management", "", AppRoutes.EMPLOYEE_MANAGEMENT),
            new DashItem("Customer Management", "", AppRoutes.CUSTOMER_MANAGEMENT),
            new DashItem("Order Management", "", AppRoutes.ORDER_MANAGEMENT),
            new DashItem("Coupon Management", "", AppRoutes.COUPON_MANAGEMENT),
            new DashItem("Skill Management", "", AppRoutes.SKILL_MANAGEMENT),
            new DashItem("Attendance Management", "", AppRoutes.ATTENDANCE_MANAGEMENT),
            new DashItem("Invoice Management", "", AppRoutes.INVOICE_MANAGEMENT),
    };
    AppButton empButton;
    AppButton custButton;
    AppButton orderButton;
    AppButton couponButton;
    AppButton skillButton;
    AppButton attendanceButton;
    AppButton invoiceButton;

    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    EmployeePage employeePage;
    EditEmployeePage editEmployeePage;
    CustomerPage customerPage;
    EditCustomerPage editCustomerPage;
    CouponPage couponPage;
    EditCouponPage editCouponPage;
    SkillPage skillPage;
    EditSkillPage editSkillPage;
    OrderPage orderPage;
    OrderDetailPage orderDetailPage;
    EditOrderPage editOrderPage;
    AttendancePage attendancePage;
    EditAttendancePage editAttendancePage;
    InvoicePage invoicePage;
    EditInvoicePage editInvoicePage;

    public DashboardPage() {
        NavigationController nav = new NavigationController(this);
        employeePage = new EmployeePage();
        editEmployeePage = new EditEmployeePage();
        new EmployeeController(employeePage, editEmployeePage, this);
        customerPage = new CustomerPage();
        editCustomerPage = new EditCustomerPage();
        new CustomerController(customerPage, editCustomerPage, this);
        couponPage = new CouponPage();
        editCouponPage = new EditCouponPage();
        new CouponController(couponPage, editCouponPage, this);
        skillPage = new SkillPage();
        editSkillPage = new EditSkillPage();
        new SkillController(skillPage, editSkillPage, this);
        orderPage = new OrderPage();
        orderDetailPage = new OrderDetailPage();
        editOrderPage = new EditOrderPage();
        new OrderController(orderPage, orderDetailPage, editOrderPage, this);
        attendancePage = new AttendancePage();
        editAttendancePage = new EditAttendancePage();
        new AttendanceController(attendancePage, editAttendancePage, this);
        invoicePage = new InvoicePage();
        editInvoicePage = new EditInvoicePage();
        new InvoiceController(invoicePage, editInvoicePage, this);

        this.setLayout(new BorderLayout());
        //
        JPanel items = new JPanel();
        items.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        items.setSize(150, HEIGHT);
        empButton = new AppButton(dashItems[0].getName());
        items.add(empButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        custButton = new AppButton(dashItems[1].getName());
        items.add(custButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        orderButton = new AppButton(dashItems[2].getName());
        items.add(orderButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        couponButton = new AppButton(dashItems[3].getName());
        items.add(couponButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        skillButton = new AppButton(dashItems[4].getName());
        items.add(skillButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        attendanceButton = new AppButton(dashItems[5].getName());
        items.add(attendanceButton);
        items.add(Box.createRigidArea(new Dimension(0, 30)));
        invoiceButton = new AppButton(dashItems[6].getName());
        items.add(invoiceButton);

        //
        container.add(employeePage, AppRoutes.EMPLOYEE_MANAGEMENT);
        container.add(editEmployeePage, AppRoutes.EDITE_EMPLOYEE);
        container.add(customerPage, AppRoutes.CUSTOMER_MANAGEMENT);
        container.add(editCustomerPage, AppRoutes.EDIT_CUSTOMER);
        container.add(couponPage, AppRoutes.COUPON_MANAGEMENT);
        container.add(editCouponPage, AppRoutes.EDIT_COUPON);
        container.add(skillPage, AppRoutes.SKILL_MANAGEMENT);
        container.add(editSkillPage, AppRoutes.EDIT_SKILL);
        container.add(orderPage, AppRoutes.ORDER_MANAGEMENT);
        container.add(orderDetailPage, AppRoutes.ORDER_DETAIL);
        container.add(editOrderPage, AppRoutes.EDIT_ORDER);
        container.add(attendancePage, AppRoutes.ATTENDANCE_MANAGEMENT);
        container.add(editAttendancePage, AppRoutes.EDIT_ATTENDANCE);
        container.add(invoicePage, AppRoutes.INVOICE_MANAGEMENT);
        container.add(editInvoicePage, AppRoutes.EDIT_INVOICE);
        this.add(items, BorderLayout.WEST);
        this.add(container, BorderLayout.CENTER);

        nav.init();
    }

    public void goTo(String route) {
        cardLayout.show(container, route);
    }

    public EmployeePage getEmployeePage() {
        return employeePage;
    }

    public EditEmployeePage getEditEmployeePage() {
        return editEmployeePage;
    }

    public AppButton getEmpButton() {
        return this.empButton;
    }

    public AppButton getCustButton() {
        return this.custButton;
    }

    public AppButton getOrderButton() {
        return this.orderButton;
    }

    public AppButton getCouponButton() {
        return this.couponButton;
    }

    public AppButton getSkillButton() {
        return this.skillButton;
    }

    public AppButton getAttendanceButton() {
        return this.attendanceButton;
    }

    public AppButton getInvoiceButton() {
        return this.invoiceButton;
    }

    public CardLayout getCardLayout() {
        return this.cardLayout;
    }

    public JPanel getPanale() {
        return this.container;
    }

}
