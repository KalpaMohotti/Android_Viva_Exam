package com.example.project_protal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_students extends AppCompatActivity {
    public String val;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);
m();
        addItemsOnSpinner2();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // String str= ContactsListView.getSelectedItem();
                //String selectedFromList = (ContactsListView.getItemAtPosition(position).toString());
                val =  parent.getAdapter().getItem(position).toString();
                Log.i("Item", "Selected: " + val);
                System.out.println(val);
               load_chatting();
            }
        });

    }
    public  void load_chatting(){
        Intent i=new Intent(this,Chating.class);
        i.putExtra("roomname",val);
        startActivity(i);
    }

    public void m(){
        listView=(ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(View_students.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    User_details user= c.getValue(User_details.class);
                    if(user.getPossition().equals("Student")){
                        String result=user.getFname()+" "+user.getLname();
                        arrayList.add(result);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);


    }


    public void search(View view){
        EditText editTextname=findViewById(R.id.editText2);
       final String name=editTextname.getText().toString();

        listView=(ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(View_students.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    User_details user= c.getValue(User_details.class);
                    if(user.getPossition().equals("Student")){
                            String fn=user.getFname();
                            String ln=user.getLname();
                            String fnln=user.getFname()+" "+user.getLname();
                        if(name.equals(fn) | name.equals(ln) | name.equals(fnln)){

                            String result=user.getFname()+" "+user.getLname();
                            arrayList.add(result);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);
    }


    List<String> list = new ArrayList<>();
    public void addItemsOnSpinner2() {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        list.add("Batch");

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Batch");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    Batch_name b= c.getValue(Batch_name.class);
                    System.out.println(b.getBatch_name());
                    System.out.println(b.getBatch_year());



                    // list = new ArrayList<>();

                    list.add(b.getBatch_name()+" - "+b.getBatch_year());





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
