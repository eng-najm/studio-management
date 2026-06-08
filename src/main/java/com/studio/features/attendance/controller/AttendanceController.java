package com.studio.features.attendance.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.studio.core.Either;
import com.studio.core.constants.AppRoutes;
import com.studio.features.attendance.AttendanceDAO;
import com.studio.features.attendance.model.AttendanceModel;
import com.studio.features.attendance.view.AttendancePage;
import com.studio.features.attendance.view.AttendanceTable;
import com.studio.features.attendance.view.EditAttendancePage;
import com.studio.features.dashboard.view.DashboardPage;

public class AttendanceController {
    private AttendancePage attendancePage;
    private EditAttendancePage editAttendancePage;
    DashboardPage route;
    private AttendanceDAO attendanceDAO;

    public AttendanceController(AttendancePage attendancePage, EditAttendancePage editAttendancePage, DashboardPage route) {
        this.attendancePage = attendancePage;
        this.editAttendancePage = editAttendancePage;
        this.route = route;
        attendanceDAO = new AttendanceDAO();
        init();
    }

    void init() {
        fetchAttendances();

        attendancePage.getCreateButton().addActionListener(e -> {
            editAttendancePage.populateEmployeeCombo();
            route.goTo(AppRoutes.EDIT_ATTENDANCE);
            editAttendancePage.setAdd(true);
        });

        attendancePage.getRefreshButton().addActionListener(e -> fetchAttendances());

        editAttendancePage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.ATTENDANCE_MANAGEMENT));
        editAttendancePage.getApplyChangeButton().addActionListener(e -> editAttendance());
        editAttendancePage.getAddButton().addActionListener(e -> addAttendance());
        editAttendancePage.getDeleteButton().addActionListener(e -> deleteAttendance());

        addTableClickListener();
    }

    void fetchAttendances() {
        Either<ArrayList<AttendanceModel>, Exception> result = attendanceDAO.getAttendances();
        if (result.isLeft()) {
            attendancePage.populateAttendanceList(result.getLeft());
        } else {
            attendancePage.populateAttendanceList(new ArrayList<>());
            System.err.println("Failed to fetch attendances: " + result.getRight().getMessage());
        }
    }

    void addAttendance() {
        AttendanceModel attendance = editAttendancePage.getCurrentData();
        int row = attendanceDAO.addAttendance(attendance);
        if (row > 0) {
            JOptionPane.showMessageDialog(attendancePage, "Successfully added attendance");
            route.goTo(AppRoutes.ATTENDANCE_MANAGEMENT);
            fetchAttendances();
        } else {
            JOptionPane.showMessageDialog(attendancePage, "Failed to add attendance");
        }
    }

    void editAttendance() {
        AttendanceModel attendance = editAttendancePage.getCurrentData();
        boolean result = attendanceDAO.updateAttendance(attendance);
        if (result) {
            JOptionPane.showMessageDialog(attendancePage, "Successfully updated attendance");
            route.goTo(AppRoutes.ATTENDANCE_MANAGEMENT);
            fetchAttendances();
        } else {
            JOptionPane.showMessageDialog(attendancePage, "Failed to update attendance");
        }
    }

    void deleteAttendance() {
        int id = editAttendancePage.getCurrentData().getId();
        if (id == 0) return;
        int confirm = JOptionPane.showConfirmDialog(editAttendancePage,
                "Are you sure you want to delete this attendance record?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        boolean result = attendanceDAO.deleteAttendance(id);
        if (result) {
            JOptionPane.showMessageDialog(attendancePage, "Successfully deleted attendance");
            route.goTo(AppRoutes.ATTENDANCE_MANAGEMENT);
            fetchAttendances();
        } else {
            JOptionPane.showMessageDialog(attendancePage, "Failed to delete attendance");
        }
    }

    void addTableClickListener() {
        JTable table = attendancePage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    AttendanceTable model = (AttendanceTable) table.getModel();
                    AttendanceModel attendance = model.getAttendanceAt(row);
                    editAttendancePage.populateEmployeeCombo();
                    editAttendancePage.setAttendanceData(attendance);
                    editAttendancePage.setAdd(false);
                    route.goTo(AppRoutes.EDIT_ATTENDANCE);
                }
            }
        });
    }
}
