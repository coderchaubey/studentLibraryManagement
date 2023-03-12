package com.example.Student._Library_Management_System.DTOs;


public class StudentMobileUpdateDTO {
    private int id;
    private String mobNo;

    public StudentMobileUpdateDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo){
        this.mobNo = mobNo;
    }
}
