package com.avb.collageproject;

public class TeacherList {
    String name;
    String UDI;
    String department;


    public TeacherList() {
    }

    public TeacherList(String name, String UDI, String department, int image) {
        this.name = name;
        this.UDI = UDI;
        this.department = department;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUDI() {
        return UDI;
    }

    public void setUDI(String UDI) {
        this.UDI = UDI;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



}
