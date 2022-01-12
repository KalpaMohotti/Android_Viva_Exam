package com.example.project_protal.Object_classes;

public class Payment_Details {
    private String Email;
    private String Batch;
    private String Fname;
    private String Lname;
    private String Amount;
    private String Month;

    public Payment_Details() {
    }

    public Payment_Details(String email, String batch, String fname, String lname, String amount, String month) {
        Email = email;
        Batch = batch;
        Fname = fname;
        Lname = lname;
        Amount = amount;
        Month = month;
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

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }
}
