package com.example.project_protal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class Dash_bord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);



            Intent i=getIntent();
//        if(i!=null){
//            if(i.getStringExtra("type").equals("Student")){
//            LinearLayout l1=(LinearLayout) findViewById(R.id.register_batch);
//            l1.setVisibility(View.GONE);
//            }
//        }


        getdata();
        LinearLayout c=(LinearLayout) findViewById(R.id.Chat);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Select_batch.class);
                i.putExtra("name","chat");
                startActivity(i);
            }
        });

        LinearLayout c1=(LinearLayout) findViewById(R.id.search);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(), com.example.project_protal.View.class);
                startActivity(ii);
            }
        });

        LinearLayout c2=(LinearLayout) findViewById(R.id.register_batch);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(getApplicationContext(), Register_batch.class);
                startActivity(iii);
            }
        });

        LinearLayout c3=(LinearLayout)findViewById(R.id.timetable);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Select_batch.class);
                i.putExtra("name","timetable");
                startActivity(i);
            }
        });
    }
    public  void getdata(){

        String name="name";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(name,"not found");


        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()) {
            Toast toast = Toast.makeText(this, e.getKey() + " " + e.getValue(), Toast.LENGTH_SHORT);
            toast.show();
            // String e=s.getString("name","not found");
            TextView t = findViewById(R.id.dash);
            if(e.getKey().equals("name")){
                t.setText(e.getValue().toString());
            }else{
                System.out.println("No matching key");
            }

        }
    }



}
