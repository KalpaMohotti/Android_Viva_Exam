package com.example.project_protal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_batch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_batch);
    }

    public void re_batch(View view){
        EditText editText_batchName=findViewById(R.id.bname);
        EditText editText_batchyear=findViewById(R.id.byear);
        String batch_name=editText_batchName.getText().toString();
        String batch_year=editText_batchyear.getText().toString();
        System.out.println(batch_name);
        System.out.println(batch_year);

        if(batch_name.equals(null) || batch_name.equals("") && batch_year.equals(null) || batch_year.equals("")){
            Toast toast=Toast.makeText(this,"Fill the from first",Toast.LENGTH_SHORT);
            toast.show();
        }else {


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference referenceuser = firebaseDatabase.getReference("Batch");
            DatabaseReference reference = referenceuser.push();

            Batch_name b = new Batch_name(batch_name, batch_year);
            reference.setValue(b);

            reference.child("batch_name").setPriority(1);
            reference.child("batch year").setPriority(2);


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
            Toast toast = Toast.makeText(this, "Your batch register sucessfully", Toast.LENGTH_SHORT);
            toast.show();
            editText_batchName.setText("");
            editText_batchyear.setText("");
        }
    }
}
