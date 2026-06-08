package com.studio.features.skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studio.core.BaseDAO;
import com.studio.core.DBHelper;
import com.studio.core.Either;
import com.studio.features.skill.model.SkillModel;

public class SkillDAO extends BaseDAO {

    public Either<ArrayList<SkillModel>, Exception> getSkills() {
        String sql = "SELECT * FROM SKILL";
        try {
            ResultSet resultSet = executeQuery(sql);
            ArrayList<SkillModel> skills = new ArrayList<>();
            while (resultSet.next()) {
                skills.add(SkillModel.fromResult(resultSet));
            }
            return Either.left(skills);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Either.right(new Exception("Failed to fetch skills"));
        }
    }

    public int addSkill(SkillModel skill) {
        String sql = "INSERT INTO SKILL (NAME) VALUES (?)";
        return executeUpdate(sql, skill.getName());
    }

    public boolean updateSkill(SkillModel skill) {
        String sql = "UPDATE SKILL SET NAME = ? WHERE ID = ?";

        try (Connection conn = DBHelper.connection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getId());
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

    public boolean deleteSkill(int id) {
        String sql = "DELETE FROM SKILL WHERE ID = ?";
        return executeUpdate(sql, id) > 0;
    }

}
