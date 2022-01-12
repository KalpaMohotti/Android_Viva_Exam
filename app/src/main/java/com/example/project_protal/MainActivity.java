package com.example.project_protal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    RelativeLayout relay1,relay2;
    NetworkChangeReceiver mNetworkReceiver;
    String fname="";
    String lname="";
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
        relay1.setVisibility(View.VISIBLE);
        relay2.setVisibility(View.VISIBLE);
        }
    };
ProgressBar loadingimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        loadingimage=(ProgressBar)findViewById(R.id.progressBar2);
        loadingimage.setVisibility(View.INVISIBLE);

        firebaseAuth=FirebaseAuth.getInstance();
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();

        if(getdata().equals("Admin")){
            admin();
        }else if(getdata().equals("Student")){
            student(getName());
        }
        relay1=(RelativeLayout) findViewById(R.id.rellay1);
        relay2=(RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable,10000);
    }


    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }





    public  String getName(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("name") ){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }

    public void signup(View view){
        Intent i=new Intent(this,Sign_up.class);
        startActivity(i);
    }

    public void forgetpass(View view){
//        Intent i= new Intent(this,Forget_password.class);
//        startActivity(i);

        final EditText resetMail=new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(view.getContext(),R.style.LightDialogTheme);
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Enter Your Email To Received Reset Link");
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mail=resetMail.getText().toString();
                if(mail.equals("")|| mail.equals(null)){

                }else {

                    firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error ! Reset Link is Not Sent", Toast.LENGTH_SHORT).show();
                        }
                    });
                } }
        });

        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        passwordResetDialog.create().show();

    }
    public void login(View view){
        EditText editTextemail=findViewById(R.id.email);
        final String email=editTextemail.getText().toString();
        EditText editTextpassword=findViewById(R.id.password);
        final String password=editTextpassword.getText().toString();







            if(email.equals("")||password.equals("")){
                Toast toast = Toast.makeText(this, "Please enter email and password", Toast.LENGTH_LONG);
                toast.show();

            }else{

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loadingimage.setVisibility(View.VISIBLE);
                            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                            DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

                            ValueEventListener listener=new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                                    for(DataSnapshot c:childSnapshots){
                                        User_details user= c.getValue(User_details.class);
                                        System.out.println(user.getEmail());
                                        System.out.println(user.getPassword());

                                        if(user.getEmail().equals(email) && user.getPassword().equals(password)&& user.getPossition().equals("Admin")) {
                                            fname=user.getFname();
                                            lname=user.getLname();
                                            save(user.getPossition());

                                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                            admin();



                                            break;
                                        } else if(user.getEmail().equals(email) && user.getPassword().equals(password)&& user.getPossition().equals("Student")){

                                            fname=user.getFname();
                                            lname=user.getLname();
                                            save(user.getPossition());
                                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                            student(user.getFname()+" "+user.getLname());



                                        }
//                                        else{
//                                            Toast.makeText(getApplicationContext(),"Password wrong",Toast.LENGTH_SHORT).show();
//
//
//                                        }


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };
                            referenceuser.addListenerForSingleValueEvent(listener);
                        }else{
                            Toast.makeText(getApplicationContext(),"Password wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }


    }
    public void admin(){
        Intent i=new Intent(this,Dash_board_navigation.class);

        startActivity(i);
    }
    public void student(String name){
        Intent i=new Intent(this,User_navigation.class);
        i.putExtra("uname",name);
        i.putExtra("type","Student");
        startActivity(i);
    }
    public  void save(String possition){
        EditText editTextemail=findViewById(R.id.email);
        final String email=editTextemail.getText().toString();
        EditText editTextpassword=findViewById(R.id.password);
        final String password=editTextpassword.getText().toString();

     //   Toast toast=Toast.makeText(this,text,Toast.LENGTH_LONG);
     //   toast.show();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putString("name",fname+" "+lname);
        editor.putString("possition",possition);
        editor.apply();
    }
    public  String getdata(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
          SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
         String value=s.getString(email,"not found");
         String value2=s.getString(password,"not found");
         String value3=s.getString(possition,"not found");



          Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
          for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
              System.out.println(e.getKey()+" "+e.getValue());
                if(e.getKey().equals("possition") && e.getValue().equals("Admin")){
                    a=e.getValue().toString();
                }else if(e.getKey().equals("possition") && e.getValue().equals("Student")){
                    a=e.getValue().toString();
                }
             // a=e.getValue().toString();
         }

            return a;
    }
}
