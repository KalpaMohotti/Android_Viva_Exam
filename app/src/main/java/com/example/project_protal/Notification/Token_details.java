package com.example.project_protal.Notification;

public class Token_details {
    String token;
    String email;
    String batch;

    public Token_details(String token, String email, String batch) {
        this.token = token;
        this.email = email;
        this.batch = batch;
    }

    public Token_details() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
