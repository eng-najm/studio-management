package com.studio.features.employee_management.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeeRowPanel extends JPanel {

    private JButton editButton;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel salaryLabel;

    // الـ Constructor الآن يستقبل كائن من نوع EmployeeModel مباشرة
    public EmployeeRowPanel(EmployeeModel employee) {

        // استخدام Layout أُفقي لترتيب العناصر بجانب بعضها
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // إعدادات الهوامش والفاصل السفلي بين الصفوف
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(8, 10, 8, 10)));
        setBackground(Color.WHITE);

        // 1. تهيئة زر التعديل (في البداية)
        editButton = new JButton("تعديل");
        editButton.addActionListener(e -> {
            // هنا يمكنك تمرير كائن الموظف بالكامل لنافذة التعديل
            openEditDialog(employee);
        });

        // 2. استخراج البيانات من الـ Object وتهيئة النصوص
        // افترضت هنا وجود دالات الـ Getter القياسية في كائن الموديل
        idLabel = new JLabel("#" + employee.getId());
        idLabel.setPreferredSize(new Dimension(60, 30));

        // دمج الاسم الثلاثي/الرباعي من كائن الموديل
        String fullName = employee.getFirstName() + " " + employee.getMeddilName() + " " + employee.getLastName();
        nameLabel = new JLabel(fullName);
        nameLabel.setPreferredSize(new Dimension(180, 30));

        // عرض الدور/المسمى الوظيفي (تأكد من وجود دالة toString مناسبة في الـ Role Enum
        // أو الكلاس)
        String roleText = (employee.getRole() != null) ? employee.getRole() : "بدون دور";
        roleLabel = new JLabel(roleText);
        roleLabel.setPreferredSize(new Dimension(120, 30));

        // عرض الراتب
        salaryLabel = new JLabel(String.valueOf(employee.getSalary()) + " $");
        salaryLabel.setPreferredSize(new Dimension(100, 30));

        // --- إضافة العناصر داخل الصف (مع مراعاة وضع الزر في البداية) ---

        add(editButton); // الزر أولاً
        add(Box.createHorizontalStrut(15));
        add(createVerticalSeparator());
        add(Box.createHorizontalStrut(15));

        add(idLabel);
        add(Box.createHorizontalStrut(15));
        add(createVerticalSeparator());
        add(Box.createHorizontalStrut(15));

        add(nameLabel);
        add(Box.createHorizontalStrut(15));
        add(createVerticalSeparator());
        add(Box.createHorizontalStrut(15));

        add(roleLabel);
        add(Box.createHorizontalStrut(15));
        add(createVerticalSeparator());
        add(Box.createHorizontalStrut(15));

        add(salaryLabel);

        // لدفع العناصر المتبقية إلى التنسيق المناسب في حال اتساع الشاشة
        add(Box.createHorizontalGlue());
    }

    // دالة مساعدة لعمل خط فاصل رأسي
    private JSeparator createVerticalSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize(new Dimension(2, 20));
        separator.setForeground(new Color(220, 220, 220)); // لون رمادي خفيف جداً لنقاوة التصميم
        return separator;
    }

    // دالة وهمية كمثال لكيفية معالجة حدث التعديل باستخدام الكائن الممرر
    private void openEditDialog(EmployeeModel employee) {
        JOptionPane.showMessageDialog(this,
                "فتح نافذة التعديل للموظف: " + employee.getFirstName() + "\nالرقم الوظيفي: " + employee.getId(),
                "تعديل بيانات",
                JOptionPane.INFORMATION_MESSAGE);
    }
}