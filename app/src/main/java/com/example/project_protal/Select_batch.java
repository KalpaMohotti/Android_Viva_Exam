package com.example.project_protal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_protal.batch_select.Batch;
import com.example.project_protal.batch_select.Batch_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
///import com.example.project_protal.batch_select.Batch;

import java.util.ArrayList;

public class Select_batch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_batch);
        Intent i=getIntent();
       final String name= i.getStringExtra("name");



        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Batch");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                ArrayList<Batch> userlist=new ArrayList<>();
                for(DataSnapshot d:childSnapshots){
                    Batch_name b=d.getValue(Batch_name.class);
                    String batch_year=b.getBatch_year();
                    String batch_name=b.getBatch_name();
                    String name_year=batch_name+" "+batch_year;

                    System.out.println(name_year);
                    userlist.add(new Batch(name_year));
                   // userlist.add(new Batch("Kalpa"));

                }
                RecyclerView recyclerView=findViewById(R.id.recyclerView3);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                Batch_adapter adapter=new Batch_adapter(userlist,name);

                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);


    }
}
