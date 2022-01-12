package com.example.project_protal;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Time_table_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_view);
        addrow();



    }

    public void buttionHide(){
         Button b=findViewById(R.id.button9);
         b.setVisibility(View.INVISIBLE);
        //  b.setText("Time table added");
    }

    public void buttonUnhide(){
        Button b=findViewById(R.id.button9);
        b.setVisibility(View.VISIBLE);
    }

    public  void addrow(){
        Intent i=getIntent();
        final String batch=i.getStringExtra("batch");
       final String type=i.getStringExtra("type");
        final TableLayout tableLayout=findViewById(R.id.table_layout);
        //TableRow row=new TableRow(this);
      final   LayoutInflater inflater=LayoutInflater.from(this);



        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Time_table");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    final String id=c.getKey();
                    Timetable_details user= c.getValue(Timetable_details.class);
                    System.out.println(user.getDate());
                    System.out.println(user.getTitle());
                    String title=user.getTitle();
                    if(title.equals(batch)){
                        System.out.println(type+"  This is type");
                            if(type.equals("Student")){

                                View row=inflater.inflate(R.layout.time_table_row,tableLayout,false);

                                TextView textView=row.findViewById(R.id.textView14);
                                textView.setText(user.getDate());

                                TextView textView1=row.findViewById(R.id.textView18);
                                textView1.setText(user.getDiscription());

                                TextView textView3=row.findViewById(R.id.textView19);
                                textView3.setText(user.getStartTime());

                                TextView textView4=row.findViewById(R.id.textView22);
                                textView4.setText(user.getEndTime());



                                tableLayout.addView(row);
                                buttionHide();
                           }else if(type.equals("Admin")){
                                View row=inflater.inflate(R.layout.time_table_row,tableLayout,false);

                                final TextView textView=row.findViewById(R.id.textView14);
                                textView.setText(user.getDate());

                                final TextView textView1=row.findViewById(R.id.textView18);
                                textView1.setText(user.getDiscription());

                                final TextView textView3=row.findViewById(R.id.textView19);
                                textView3.setText(user.getStartTime());

                                final TextView textView4=row.findViewById(R.id.textView22);
                                textView4.setText(user.getEndTime());




                                tableLayout.addView(row);

                                Button b=row.findViewById(R.id.button9);
                                b.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       // System.out.println("buttion click listner here");
                                       // FirebaseDatabase database=FirebaseDatabase.getInstance();

                                        //DatabaseReference reference=database.getReference("Time_table");

                                 //call time table remove method
                                    remove(batch,textView.getText().toString(),textView1.getText().toString(),textView3.getText().toString(),textView4.getText().toString(),id);








                                       // reference.setValue(null);
                                    }
                                });
                            }


buttonUnhide();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);



    }
    DatabaseReference mref;
    public void remove(String batch,String date,String des,String startTime,String endTime,String id) {


//Delete value
      try {
          FirebaseDatabase.getInstance().getReference().child("Time_table").child(id).removeValue();

          Toast toast=Toast.makeText(this," Sucessfully Deleted time table",Toast.LENGTH_SHORT);
          toast.show();

          Intent i=new Intent(this,Time_table_view.class);
          i.putExtra("batch",batch);
          i.putExtra("type","Admin");
          startActivity(i);
          this.finish();
      }catch (Exception e){

      }


    }
}