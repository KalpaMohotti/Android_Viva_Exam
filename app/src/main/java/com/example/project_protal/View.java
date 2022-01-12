package com.example.project_protal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        final EditText editText=findViewById(R.id.editText3);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("kalpa");
              final String textname = editText.getText().toString();

                final ArrayList<User_object_data> userlist=new ArrayList<>();

                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

                ValueEventListener listener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                        for(DataSnapshot c:childSnapshots){
                            User_details user= c.getValue(User_details.class);
                            if(user.getPossition().equals("Student")){
                                String fullName=user.getFname()+" "+user.getLname();
                                if(textname.equals(user.getFname()) | textname.equals(user.getLname()) | textname.equals(fullName)){
                                    String result=user.getFname()+" "+user.getLname();
                                    userlist.add(new User_object_data(user.getFname(),user.getLname(),user.getEmail(),user.getYear()));
                                }


                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                referenceuser.addListenerForSingleValueEvent(listener);
                RecyclerView recyclerView=findViewById(R.id.recycleview);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                User_adapter adapter=new User_adapter(userlist);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        m();
    }
    public void m(){
        final ArrayList<User_object_data> userlist=new ArrayList<>();

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
                    userlist.add(new User_object_data(user.getFname(),user.getLname(),user.getEmail(),user.getYear()));

                }

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
        referenceuser.addListenerForSingleValueEvent(listener);
    RecyclerView recyclerView=findViewById(R.id.recycleview);
    LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    User_adapter adapter=new User_adapter(userlist);

        recyclerView.setAdapter(adapter);

}



}
