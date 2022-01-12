package com.example.project_protal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Change_password extends AppCompatActivity {
String email_value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent i=getIntent();
       email_value=  i.getStringExtra("email");
        //email_value="kasun7521madushanka@gmail.com";
      //  System.out.println(email_value);

    }
    public void Passwordchange(View view){
        EditText new_password=findViewById(R.id.new_pass1);
        EditText confirm_pass=findViewById(R.id.new_pass2);

        final String String_pass=new_password.getText().toString().trim();
        String String_confirmPass=confirm_pass.getText().toString().trim();

        if(String_pass.equals(String_confirmPass)){
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference=firebaseDatabase.getReference("User_details");
            ValueEventListener listener=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> dataSnap=dataSnapshot.getChildren();
                    for(DataSnapshot d:dataSnap){
                        User_details ud=d.getValue(User_details.class);
                        if(ud.getEmail().equals(email_value)){
                            ud.setPassword(String_pass);
                                    String key=d.getKey();
                    //    dataSnapshot.getRef().child("password").setValue(String_pass);

                        ChangePass(ud.getYear(),ud.getFname(),ud.getLname(),ud.getEmail(),ud.getPassword(),ud.getPossition(),ud.getMobile(),key,String_pass);
                            Toast.makeText(Change_password.this,ud.getPassword(),Toast.LENGTH_SHORT).show();
////                      Intent i=new Intent(Change_password.this,MainActivity.class);
////                      startActivity(i);



                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            databaseReference.addListenerForSingleValueEvent(listener);
        }
    }


   public void ChangePass(String year,String fname,String lname,String email,String password,String possition,String mobile,String key,String newpass){
       System.out.println(key);
       System.out.println(newpass);

       FirebaseDatabase.getInstance().getReference().child("User_details").child(key).removeValue();

      updateAgain(fname,lname,year,email,newpass,possition,mobile);


   }

   public void updateAgain(String fname,String lname,String year,String email,String newpass,String possition,String mobile){
       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       DatabaseReference referenceuser = firebaseDatabase.getReference("User_details");
       DatabaseReference reference = referenceuser.push();

       User_details u = new User_details(fname, lname, year,email, newpass, possition,mobile);
       reference.setValue(u);

       reference.child("year").setPriority(1);
       reference.child("fname").setPriority(2);
       reference.child("lname").setPriority(3);
       reference.child("email").setPriority(4);

       reference.child("password").setPriority(5);
       reference.child("possition").setPriority(6);
       reference.child("mobile").setPriority(7);

       ChildEventListener Listener=new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               Toast toast = Toast.makeText(Change_password.this, "Your password changed", Toast.LENGTH_SHORT);
               Intent i = new Intent(Change_password.this, MainActivity.class);
               startActivity(i);


           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       };
       reference.addChildEventListener(Listener);
   }
}
