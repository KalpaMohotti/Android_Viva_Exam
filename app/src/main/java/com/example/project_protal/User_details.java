package com.example.project_protal;

public class User_details {
    private String Fname;
    private String Lname;
    private String Year;
    private String Email;
    private String Password;
    private String Possition;
    private String Mobile;
    //private String Batch;
    public  User_details(){

    }

    public User_details(String fname, String lname, String year, String email, String password, String possition, String mobile) {
        Fname = fname;
        Lname = lname;
        Year = year;
        Email = email;
        Password = password;
        Possition = possition;
        Mobile = mobile;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPossition() {
        return Possition;
    }

    public void setPossition(String possition) {
        Possition = possition;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}