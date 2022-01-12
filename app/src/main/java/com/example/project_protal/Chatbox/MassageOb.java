package com.example.project_protal.Chatbox;

public class MassageOb {
   private String Massage;
   private  String mObjemail;

    public MassageOb(String massage, String mObjemail) {
        Massage = massage;
        this.mObjemail = mObjemail;
    }

    public String getMassage() {
        return Massage;
    }

    public void setMassage(String massage) {
        Massage = massage;
    }

    public String getmObjemail() {
        return mObjemail;
    }

    public void setmObjemail(String mObjemail) {
        this.mObjemail = mObjemail;
    }
}
