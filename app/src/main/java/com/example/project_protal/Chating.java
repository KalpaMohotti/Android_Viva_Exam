package com.example.project_protal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.project_protal.R.id.listChat;



public class Chating extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
m();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

               //  String str= ContactsListView.getSelectedItem();
              //  String selectedFromList = (ContactsListView.getItemAtPosition(position).toString());
                val =  parent.getAdapter().getItem(position).toString();
                Log.i("Item", "Selected: " + val);
                System.out.println(val);
                send_selecterdBatch(val);
            }
        });
    }

    public  void send_selecterdBatch(String value){
        Intent intent=new Intent(this,Send.class);
        intent.putExtra("name",value);
        startActivity(intent);
    }

    public  void  add_chatroom(View view){
        Intent ii=new Intent(this,View_students.class);
        startActivity(ii);

    }


    public void m(){
        listView=(ListView) findViewById(listChat);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Chating.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Batch");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    Batch_name b= c.getValue(Batch_name.class);

                        String result=b.getBatch_name()+" "+b.getBatch_year();
                        arrayList.add(result);
                        adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);


    }


}
