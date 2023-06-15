package com.avb.collageproject;

public class AddTeachersData {
    String name;
    String UDI;
    String designation;
    String status;
    String department;
    String stream;
    String contactno;
    String address;

    public AddTeachersData() {
    }

    public AddTeachersData(String name, String UDI, String designation, String status, String department, String stream, String contactno, String address) {
        this.name = name;
        this.UDI = UDI;
        this.designation = designation;
        this.status = status;
        this.department = department;
        this.stream = stream;
        this.contactno = contactno;
        this.address = address;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
