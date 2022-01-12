package com.example.project_protal.Object_classes;

public class Fees_Details {
   private String Batch;
   private String Payment;

    public Fees_Details() {
    }

    public Fees_Details(String batch, String payment) {
        Batch = batch;
        Payment = payment;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }
}
