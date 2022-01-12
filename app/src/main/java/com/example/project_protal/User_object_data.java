package com.example.project_protal;

public class User_object_data {
    private String Fname;
    private String Lname;
    private String Email;
    private String Batch;

    public User_object_data(String fname, String lname, String email, String batch) {
        Fname = fname;
        Lname = lname;
        Email = email;
        Batch = batch;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }
}
