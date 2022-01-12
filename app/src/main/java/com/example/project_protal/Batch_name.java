package com.example.project_protal;

public class Batch_name {
    private String Batch_name;
    private String Batch_year;

    public Batch_name(){

    }

    public Batch_name(String batch_name, String batch_year) {
        Batch_name = batch_name;
        Batch_year = batch_year;
    }

    public String getBatch_name() {
        return Batch_name;
    }

    public void setBatch_name(String batch_name) {
        Batch_name = batch_name;
    }

    public String getBatch_year() {
        return Batch_year;
    }

    public void setBatch_year(String batch_year) {
        Batch_year = batch_year;
    }
}
