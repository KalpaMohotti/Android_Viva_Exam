package com.example.project_protal.Object_classes;

public class ChartDetails {
  private   String batch;
   private String payment;

    public ChartDetails(String batch, String payment) {
        this.batch = batch;
        this.payment = payment;
    }



    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
