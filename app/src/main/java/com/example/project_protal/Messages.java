package com.example.project_protal;

public class Messages {
    private String Batch_name;
    private String Message;
    private String Date;
    private String Email;

    public  Messages(){

    }

    public Messages(String batch_name, String message, String date, String email) {
        Batch_name = batch_name;
        Message = message;
        Date = date;
        Email = email;
    }

    public String getBatch_name() {
        return Batch_name;
    }

    public void setBatch_name(String batch_name) {
        Batch_name = batch_name;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
