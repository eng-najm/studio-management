package com.studio.features.employee_management.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.studio.core.shared_widgets.AppLable;
import com.studio.features.employee_management.model.EmployeeModel;

public class EmployeePage extends JPanel {
    JPanel listPanel;
    private JScrollPane scrollPane;

    public EmployeePage() {

        this.setSize(1000, 1000);
        setLayout(new BorderLayout());

        this.add(new AppLable("Employee Management"), BorderLayout.NORTH);

        // 2. تهيئة الـ listPanel وتحديد تخطيط رأسي (Y_AXIS) لترتيب الصفوف تحت بعضها
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        // 3. إنشاء الـ JScrollPane وتمرير الـ listPanel بداخلها
        scrollPane = new JScrollPane(listPanel);

        // إعدادات شريط التمرير (تظهر فقط عند الحاجة)
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // لمنع التمرير الأفقي

        // تحسين سرعة التمرير بالفأرة (مهم جداً لتجربة مستخدم سلسة)
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // إزالة الحواف الافتراضية إذا كنت تريد تصميماً فلات (اختياري)
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // 4. إضافة الـ scrollPane في المنتصف لكي تأخذ المساحة المتاحة بالكامل
        add(scrollPane, BorderLayout.CENTER);
    }

    public void populateEmployeeList(List<EmployeeModel> employees) {
        // مسح العناصر القديمة إن وجدت
        listPanel.removeAll();

        // إضافة صف لكل موظف
        for (EmployeeModel emp : employees) {
            listPanel.add(new EmployeeRowPanel(emp));
        }

        /*
         * * إضافة 'Glue' رأسي في النهاية لمنع تمدد الصفوف عمودياً
         * إذا كان عدد الموظفين قليلاً (يحافظ على الحجم الثابت لكل صف)
         */
        listPanel.add(Box.createVerticalGlue());

        // تحديث الواجهة الرسومية لإظهار العناصر الجديدة وشريط التمرير
        listPanel.revalidate();
        listPanel.repaint();
    }

}