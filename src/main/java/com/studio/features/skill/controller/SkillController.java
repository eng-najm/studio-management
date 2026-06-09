package com.studio.features.skill.controller;

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
import com.studio.features.skill.SkillDAO;
import com.studio.features.skill.model.SkillModel;
import com.studio.features.skill.view.EditSkillPage;
import com.studio.features.skill.view.SkillPage;
import com.studio.features.skill.view.SkillTable;

public class SkillController {
    private SkillPage skillPage;
    private EditSkillPage editSkillPage;
    DashboardPage route;
    private SkillDAO skillDAO;

    public SkillController(SkillPage skillPage, EditSkillPage editSkillPage, DashboardPage route) {
        this.skillPage = skillPage;
        this.editSkillPage = editSkillPage;
        this.route = route;
        skillDAO = new SkillDAO();
        init();
    }

    void init() {
        fetchSkills();
        editSkillPage.getApplyChangeButton().addActionListener(e -> editSkill());
        editSkillPage.getAddButton().addActionListener(e -> addSkill());
        editSkillPage.getBackButton().addActionListener(e -> route.goTo(AppRoutes.SKILL_MANAGEMENT));
        skillPage.getCreateButton().addActionListener(e -> {
            route.goTo(AppRoutes.EDIT_SKILL);
            editSkillPage.setAdd(true);
        });
        skillPage.getRefreshButton().addActionListener(e -> fetchSkills());
        editSkillPage.getDeleteButton().addActionListener(e -> deleteSkill());
        addTableRowClickListener();
    }

    void fetchSkills() {
        Either<ArrayList<SkillModel>, Exception> result = skillDAO.getSkills();
        if (result.isLeft()) {
            skillPage.populateSkillList(result.getLeft());
        } else {
            skillPage.populateSkillList(new ArrayList<>());
            System.err.println("Failed to fetch skills: " + result.getRight().getMessage());
        }
    }

    void editSkill() {
        List<String> errors = editSkillPage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(skillPage, errors);
            return;
        }
        boolean result = skillDAO.updateSkill(editSkillPage.getCurrentData());
        if (result) {
            JOptionPane.showMessageDialog(skillPage, "Successfully updated skill");
        } else {
            JOptionPane.showMessageDialog(skillPage, "Failed to update skill");
        }
    }

    void addSkill() {
        List<String> errors = editSkillPage.validateFields();
        if (!errors.isEmpty()) {
            FieldValidator.showErrors(skillPage, errors);
            return;
        }
        int row = skillDAO.addSkill(editSkillPage.getCurrentData());
        if (row > 0) {
            JOptionPane.showMessageDialog(skillPage, "Successfully added skill");
        } else {
            JOptionPane.showMessageDialog(skillPage, "Failed to add skill");
        }
    }

    void deleteSkill() {
        int id = editSkillPage.getCurrentData().getId();
        if (id == 0) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(skillPage,
                "Are you sure you want to delete this skill?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        boolean result = skillDAO.deleteSkill(id);
        if (result) {
            JOptionPane.showMessageDialog(skillPage, "Successfully deleted skill");
            route.goTo(AppRoutes.SKILL_MANAGEMENT);
        } else {
            JOptionPane.showMessageDialog(skillPage, "Failed to delete skill");
        }
    }

    private void addTableRowClickListener() {
        JTable table = skillPage.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    SkillTable model = (SkillTable) table.getModel();
                    editSkillPage.setSkillData(model.getSkillAt(row));
                    route.goTo(AppRoutes.EDIT_SKILL);
                    editSkillPage.setAdd(false);
                }
            }
        });
    }
}
