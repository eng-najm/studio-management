package com.studio.module;

public class Employee_skill {

private int employeeId;
private int skillId;    
private int years;
public Employee_skill(int employeeId, int skillId, int years) {
    this.employeeId = employeeId;
    this.skillId = skillId;
    this.years = years;
}
public int getEmployeeId() {
    return employeeId;
}
public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
}
public int getSkillId() {
    return skillId;
}
public void setSkillId(int skillId) {
    this.skillId = skillId;
}
public int getYears() {
    return years;
}
public void setYears(int years) {
        // التحقق مما إذا كان الرقم أصغر من صفر
    if (years < 0) {
        throw new IllegalArgumentException("Years of experience cannot be negative.");
        
    }else {
        this.years = years;
    }
   

}




}