package com.avb.collageproject;

public class StaffList {
    String name;
    String udi;
    String department;
    int image;

    public StaffList(String name, String udi, String department, int image) {
        this.name = name;
        this.udi = udi;
        this.department = department;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUdi() {
        return udi;
    }

    public void setUdi(String udi) {
        this.udi = udi;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public StaffList() {
    }


}
