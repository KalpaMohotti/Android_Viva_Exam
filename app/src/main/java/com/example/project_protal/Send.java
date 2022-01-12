package com.example.project_protal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class Send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        setName();
        m();


    }
    public void setName(){
        Intent i=getIntent();
       String name= i.getStringExtra("name");
        TextView ed=findViewById(R.id.textView4);
        ed.setText(name);

    }
    String date;
    String batch_name;
    String message;
    public void Send_message(View view){
        TextView textViewname=findViewById(R.id.textView4);
        EditText editTextmessage=findViewById(R.id.editText);

         batch_name=textViewname.getText().toString();
         message=editTextmessage.getText().toString();

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
         date = df.format(Calendar.getInstance().getTime());
        System.out.println(date);
        System.out.println(batch_name);
        System.out.println(message);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");
        DatabaseReference reference=referenceuser.push();

        Messages b=new Messages(batch_name,message,date,getEmail());
        reference.setValue(b);

        reference.child("batch_name").setPriority(1);
        reference.child("message").setPriority(2);

        ChildEventListener childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               // change_value(batch_name,message,date);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //change_value(batch_name,message,date);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               // change_value(batch_name,message,date);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addChildEventListener(childEventListener);
        Toast toast=Toast.makeText(this,"Your Message send sucessful",Toast.LENGTH_SHORT);
        toast.show();
        addFagment1();
       // m();
       // change_value(batch_name,message,date);

    }


    public  void change_value(String b,String m,String d){

        final LinearLayout linearLayout=findViewById(R.id.container1);
        System.out.println(b+" "+m+" "+d);
        String result="Me : "+m+" : "+d;
        TextView textView=new TextView(Send.this);

        textView.setText(result);
        linearLayout.addView(textView);


    }

    public void m(){
                final TextView messagetext=findViewById(R.id.textView6);
        final LinearLayout linearLayout=findViewById(R.id.container1);

                TextView textViewbatch=findViewById(R.id.textView4);
                final String batch=textViewbatch.getText().toString();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    Messages user= c.getValue(Messages.class);
                    if(user.getBatch_name().equals(batch)){
                        String result="Me : "+user.getMessage()+" : "+user.getDate();
                        System.out.println(result);
                        TextView textView=new TextView(Send.this);

                        textView.setText(result);
                        linearLayout.addView(textView);
                       //setContentView(linearLayout);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);


    }


    public  void addFagment1(){
        Fragment_messages fragment1=new Fragment_messages();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.container1,fragment1,"f1");
        transaction.commit();
    }
    public  String getEmail(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("email") ){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }
}
