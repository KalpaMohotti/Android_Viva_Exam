package com.example.project_protal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_protal.Object_classes.Fees_Details;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manage_Fees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__fees);
        addItemsOnSpinner2();
        addrow();
    }
    List<String> list = new ArrayList<>();
    public void addItemsOnSpinner2() {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
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

    public void Submit(View view){
        EditText fee=findViewById(R.id.editText15);
        Spinner spinner=findViewById(R.id.spinner3);

       final String fees= fee.getText().toString();


       final String batch=spinner.getSelectedItem().toString();

        if(batch.equals("Batch")){
            Toast toast = Toast.makeText(this, "Please Select The Batch First", Toast.LENGTH_SHORT);
            toast.show();
        }else{

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference
                    .child("Fees")
                    .orderByChild("batch")
                    .equalTo(batch);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.getChildrenCount()>0) {
                    if(dataSnapshot.exists()) {

                        Toast toast = Toast.makeText(getApplicationContext(), "This batch already added to payment. Try another batch", Toast.LENGTH_SHORT);
                        toast.show();

                    }else{

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference referenceuser = firebaseDatabase.getReference("Fees");
                        DatabaseReference reference1 = referenceuser.push();

                        Fees_Details fees_details=new Fees_Details(batch,forcurrency(fees));
                        reference1.setValue(fees_details);

                        reference1.child("batch").setPriority(1);
                        reference1.child("payment").setPriority(2);


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
                        reference1.addChildEventListener(childEventListener);
                        Toast toast = Toast.makeText(getApplicationContext(), "Fees Added Successfully", Toast.LENGTH_SHORT);
                        toast.show();

                        addrow();

                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            fee.setText("");
             spinner.setSelection(0);


        }
    }

    public static String forcurrency(String amount) {
        DecimalFormat formatter = new DecimalFormat("##.00");
        return formatter.format(Double.parseDouble(amount));

    }



    public  void addrow(){


        final TableLayout tableLayout=findViewById(R.id.layout);
        //TableRow row=new TableRow(this);
        final LayoutInflater inflater=LayoutInflater.from(this);
        tableLayout.removeViews(1, Math.max(0, tableLayout.getChildCount() - 1));


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Fees");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    final String id=c.getKey();
                    Fees_Details user= c.getValue(Fees_Details.class);



                            View row=inflater.inflate(R.layout.payment_add_row,tableLayout,false);

                            final TextView textView=row.findViewById(R.id.textView46);
                            textView.setText(user.getBatch());

                            final TextView textView1=row.findViewById(R.id.textView47);
                            textView1.setText(user.getPayment());
                    System.out.println(user.getPayment());
                    System.out.println(user.getBatch());





                            tableLayout.addView(row);

                            Button b=row.findViewById(R.id.button6);
                            b.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    final EditText resetMail=new EditText(v.getContext());

                                    AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(v.getContext(),R.style.LightDialogTheme);
                                    passwordResetDialog.setTitle("Update Fees");
                                    passwordResetDialog.setMessage("Enter new fees");
                                    passwordResetDialog.setView(resetMail);

                                    passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String newValue=resetMail.getText().toString();




                                            HashMap<String, Object> result = new HashMap<>();
                                            result.put("payment", forcurrency(newValue));

                                            FirebaseDatabase.getInstance().getReference().child("Fees").child(id).updateChildren(result);
                                            Toast toast = Toast.makeText(getApplicationContext(), "Fees Update Successfully", Toast.LENGTH_SHORT);
                                            toast.show();
                                            addrow();
                                            }
                                    });

                                    passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    passwordResetDialog.create().show();
                                }
                            });






                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);



    }
}
