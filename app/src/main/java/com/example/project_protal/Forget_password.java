package com.example.project_protal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.project_protal.email.JavaMailAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Forget_password extends AppCompatActivity {
public int code=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EditText t=findViewById(R.id.code1);
        Button b=findViewById(R.id.verification_ok);
        t.setVisibility(View.GONE);
        b.setVisibility(View.GONE);

        Button  fab=findViewById(R.id.send_buttion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sendemail();
            }
        });
    }

String sendEmail="";
    public void Sendemail(){
        EditText mobile=findViewById(R.id.editText5);
        final String mobileNo=mobile.getText().toString();

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("User_details");
        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Iterable<DataSnapshot> iterable= dataSnapshot.getChildren();
               for(DataSnapshot c:iterable){
                  User_details ud=c.getValue(User_details.class);
                  if(ud.getMobile().equals(mobileNo)){
                      System.out.println(ud.getEmail());
                        sendEmail=ud.getEmail();
                      long a=System.currentTimeMillis();
                        String timecode=String.valueOf(a);


                      code=Integer.parseInt(timecode.substring(7));
                      final String massage="Your Virification No-"+code;
                      final String email=ud.getEmail();
                      final String subject="Student Protal";

                      //Send email
//                      JavaMailAPI javaMailAPI=new JavaMailAPI(Forget_password.this,email,subject,massage);
//                      javaMailAPI.execute();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                               // sendEmail();
                                JavaMailAPI javaMailAPI=new JavaMailAPI(Forget_password.this,email,subject,massage);
                     javaMailAPI.execute();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                      EditText t=findViewById(R.id.code1);
                      Button b=findViewById(R.id.verification_ok);
                      t.setVisibility(View.VISIBLE);
                      b.setVisibility(View.VISIBLE);

                  }else{
                      Toast.makeText(Forget_password.this,"Please Enter Correct Number",Toast.LENGTH_SHORT).show();
                  }
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(listener);



    }







    public void Checkcode(View view){
        EditText text=findViewById(R.id.code1);
        String text1=text.getText().toString();
        int a=Integer.parseInt(text1);
        if(a==code){
            Toast.makeText(this,"Verification code match",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,Change_password.class);
            i.putExtra("email",sendEmail);
            startActivity(i);

        }else{
            Toast.makeText(this,"Verification code Error.Please try again",Toast.LENGTH_SHORT).show();
        }
    }
}
