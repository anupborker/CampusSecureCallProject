package com.avb.collageproject;

public class StudentsList {
    String name;
    String faculty;
    String rollno;
    int image;
    public StudentsList() {
        // Required empty constructor
    }

    public StudentsList(String name, String faculty, String rollno, int image) {
        this.name = name;
        this.faculty = faculty;
        this.rollno = rollno;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
