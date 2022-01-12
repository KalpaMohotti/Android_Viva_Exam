package com.example.project_protal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class User extends AppCompatActivity {
 String batch="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent i=getIntent();
        String a=i.getStringExtra("uname");
        String b=i.getStringExtra("type");
        TextView t=findViewById(R.id.dash);
       // TextView type=findViewById(R.id.type);
       // type.setText(b);
       // t.setText(a);
        t.setText(getUsername());



        TextView text=findViewById(R.id.textView5);
        final String batchname=text.getText().toString();
        System.out.println("batch_name_print    --"+textViewget() );
        LinearLayout c=(LinearLayout) findViewById(R.id.Chat);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Massage_send.class);
                i.putExtra("name",textViewget());

                startActivity(i);
            }
        });


        LinearLayout c3=(LinearLayout)findViewById(R.id.timetable);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Time_table_view.class);
                i.putExtra("type","Student");
                i.putExtra("batch",textViewget());
                startActivity(i);
            }
        });

        LinearLayout location=(LinearLayout)findViewById(R.id.location_class);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),view_location.class);
                startActivity(i);
            }
        });

        max();


    }
    public void max(){



            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

            ValueEventListener listener=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                    for(DataSnapshot c:childSnapshots){
                        User_details user= c.getValue(User_details.class);

                        //System.out.println(user.getEmail());
                       // System.out.println(user.getYear());
                        System.out.println(getdata());
                        if(user.getEmail().equals(getdata())){
                            System.out.println(user.getYear()+"1");
                            batch=user.getYear();
                            System.out.println(batch);
                            textViewset();
                        }


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            referenceuser.addListenerForSingleValueEvent(listener);
            //t.setText(batch);

        }
    public void textViewset(){
        TextView t=findViewById(R.id.textView5);
        t.setText(batch);
    }
    public String textViewget(){
        TextView t=findViewById(R.id.textView5);
        String text=t.getText().toString();
        return text;
    }


    public  String getdata(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
          //  System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("email")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }

    public  String getUsername(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            //  System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("name")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }
}
