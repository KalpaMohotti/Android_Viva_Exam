package com.example.project_protal;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_protal.Notification.Token_details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;



public class Sign_up extends AppCompatActivity {
FirebaseAuth firebaseAuth;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        addItemsOnSpinner2();


    }





    public void signup_data(View v) {
        EditText editTextfname = findViewById(R.id.editText9);
        EditText editTextlname = findViewById(R.id.editText5);
        EditText editTextemail = findViewById(R.id.editText7);
        EditText editTextpassword = findViewById(R.id.editText8);
        EditText editTextcpassword = findViewById(R.id.editText4);
        EditText mobile = findViewById(R.id.mobile);
        Spinner sp = findViewById(R.id.spinner);



       final String year = sp.getSelectedItem().toString();
      final  String fname = editTextfname.getText().toString();
      final  String lname = editTextlname.getText().toString();
      final  String email = editTextemail.getText().toString();
      final  String password = editTextpassword.getText().toString();
      final  String cpassword = editTextcpassword.getText().toString();
      final  String mobileNo=mobile.getText().toString();
       final String possition = "Student";
        if (year.equals(null) || year.equals("") ||year.equals("Batch") && fname.equals(null) || fname.equals("") && lname.equals(null) || lname.equals("") && email.equals(null) || email.equals("") || password.equals(null) || password.equals("") && cpassword.equals(null) || cpassword.equals("")) {
            Toast toast = Toast.makeText(this, "Fill the from first", Toast.LENGTH_SHORT);
            toast.show();
        } else {



        if (password.equals(cpassword)) {
firebaseAuth=FirebaseAuth.getInstance();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                    if(task.isSuccessful()){
                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                            @Override
                            public void onSuccess(InstanceIdResult instanceIdResult) {
                                String token = instanceIdResult.getToken();
                              DatabaseReference referencetoken=firebaseDatabase.getReference("Tokens");
                              DatabaseReference re=referencetoken.push();

                                Token_details t=new Token_details(token,email,year);
                              re.setValue(t);

                              re.child("token").setPriority(1);
                                re.child("email").setPriority(2);
                                re.child("year").setPriority(3);
                              ChildEventListener listener=new ChildEventListener() {
                                  @Override
                                  public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                  }

                                  @Override
                                  public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                  }

                                  @Override
                                  public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                  }

                                  @Override
                                  public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                  }

                                  @Override
                                  public void onCancelled(@NonNull DatabaseError error) {

                                  }
                              }  ;
                              re.addChildEventListener(listener);
                            }
                        });



                        DatabaseReference referenceuser = firebaseDatabase.getReference("User_details");
                        DatabaseReference reference = referenceuser.push();

                        User_details u = new User_details(fname, lname, year,email, password, possition,mobileNo);
                        reference.setValue(u);

                        reference.child("year").setPriority(1);
                        reference.child("fname").setPriority(2);
                        reference.child("lname").setPriority(3);
                        reference.child("email").setPriority(4);

                        reference.child("password").setPriority(5);
                        reference.child("possition").setPriority(6);
                        reference.child("mobile").setPriority(7);

                        ChildEventListener childEventListener = new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
                        reference.addChildEventListener(childEventListener);
                        Toast toast = Toast.makeText(getApplicationContext(), "Sign up Success", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Can't signup, Please try again", Toast.LENGTH_SHORT);
                   toast.show();
                    }
                }
            });


        } else {
            Toast toast = Toast.makeText(this, "Your confirm password incorrect.Please try again", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    }




    List<String> list = new ArrayList<>();
    public void addItemsOnSpinner2() {
            Spinner spinner2 = (Spinner) findViewById(R.id.spinner);
            list.add("Batch");

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference referenceuser = firebaseDatabase.getReference("Batch");

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> childSnapshots = dataSnapshot.getChildren();
                    for (DataSnapshot c : childSnapshots) {
                        Batch_name b = c.getValue(Batch_name.class);
                        System.out.println(b.getBatch_name());
                        System.out.println(b.getBatch_year());


                        // list = new ArrayList<>();

                        list.add(b.getBatch_name() + " " + b.getBatch_year());


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            referenceuser.addListenerForSingleValueEvent(listener);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);


    }
}
