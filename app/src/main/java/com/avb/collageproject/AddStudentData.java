package com.avb.collageproject;


public class AddStudentData {



    String name;
    String faculty;
    String rollno;
    String address;
    String contacno;
    String acedemicyear;
    String semester;

    public AddStudentData(String name, String faculty, String rollno, String address, String contacno, String acedemicyear, String semester) {
        this.name = name;
        this.faculty = faculty;
        this.rollno = rollno;
        this.address = address;
        this.contacno = contacno;
        this.acedemicyear = acedemicyear;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacno() {
        return contacno;
    }

    public void setContacno(String contacno) {
        this.contacno = contacno;
    }

    public String getAcedemicyear() {
        return acedemicyear;
    }

    public void setAcedemicyear(String acedemicyear) {
        this.acedemicyear = acedemicyear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }



}
