package com.studio.features.skill.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillModel {

    private int id;
    private String name;

    public SkillModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SkillModel(String name) {
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SkillModel fromResult(ResultSet rs) throws SQLException {
        return new SkillModel(
                rs.getInt("ID"),
                rs.getString("NAME"));
    }

}
