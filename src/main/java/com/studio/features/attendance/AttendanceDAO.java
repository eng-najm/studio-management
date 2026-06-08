package com.studio.features.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.attendance.model.AttendanceModel;

public class AttendanceDAO extends BaseDAO {

    public Either<ArrayList<AttendanceModel>, Exception> getAttendances() {
        String sql = "SELECT * FROM ATTENDANCE";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<AttendanceModel> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(AttendanceModel.fromResult(resultSet));
            }
            return Either.left(list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(e);
        }
    }

    public int addAttendance(AttendanceModel attendance) {
        String sql = "INSERT INTO ATTENDANCE (EMP_ID, CHECK_IN, CHECK_OUT, \"DATE\", STATUS) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                attendance.getEmpId(),
                attendance.getCheckIn(),
                attendance.getCheckOut(),
                attendance.getDate(),
                attendance.getStatus());
    }

    public boolean updateAttendance(AttendanceModel attendance) {
        String sql = "UPDATE ATTENDANCE SET EMP_ID = ?, CHECK_IN = ?, CHECK_OUT = ?, \"DATE\" = ?, STATUS = ? WHERE ID = ?";
        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, attendance.getEmpId());
                ps.setTimestamp(2, attendance.getCheckIn());
                ps.setTimestamp(3, attendance.getCheckOut());
                ps.setDate(4, new java.sql.Date(attendance.getDate().getTime()));
                ps.setInt(5, attendance.getStatus());
                ps.setInt(6, attendance.getId());
                ps.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteAttendance(int id) {
        String sql = "DELETE FROM ATTENDANCE WHERE ID = ?";
        return executeUpdate(sql, id) > 0;
    }
}
