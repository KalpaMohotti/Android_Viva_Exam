package com.example.project_protal;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Message;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_protal.Chatbox.MassageAdapter;
import com.example.project_protal.Chatbox.MassageOb;

import com.example.project_protal.Notification.Token_details;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Massage_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage_view);
        setName();



        loadPastMassages();



    }




    public String batchtextView(){
        TextView textViewbatch=findViewById(R.id.textView2);
        String batch=textViewbatch.getText().toString();
        return batch;
    }

    public void setName(){
        Intent i=getIntent();
        String name= i.getStringExtra("name");
        TextView ed=findViewById(R.id.textView2);
        ed.setText(name);

    }



RecyclerView recyclerView;
    ArrayList<MassageOb> massagelist;
    MassageAdapter massageAdapter;
    public void loadPastMassages(){



        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");


        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                 massagelist=new ArrayList<>();
                for(DataSnapshot c:childSnapshots){
                    Messages user= c.getValue(Messages.class);
                    System.out.println(user.getBatch_name());

                    //System.out.println(batchtextView());


                    if(user.getBatch_name().equals(batchtextView())){
                        massagelist.add(new MassageOb(user.getMessage(),user.getEmail()));

//                        if(user.getEmail().equals(getEmail())){

                             recyclerView=findViewById(R.id.recyclerView);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                             massageAdapter=new MassageAdapter(massagelist,getEmail());


                            recyclerView.setAdapter(massageAdapter);

                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                        
//
//                        }else{
////                            massagelist.add(new MassageOb(user.getMessage()));
////                            RecyclerView recyclerView=findViewById(R.id.recyclerView);
////                            LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
////
////                            recyclerView.setLayoutManager(layoutManager);
////                           MassageAdapterReceiver massageAdapterReceiver=new MassageAdapterReceiver(massagelist);
////                            recyclerView.setAdapter(massageAdapterReceiver);
//
//                        }


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);



    }

    private void clearRecyclerView() {
        massagelist.clear();
        massageAdapter.notifyDataSetChanged();
    }




    String date;
    String batch_name;
    String message;
    public void Send_message(View view){
        TextView textViewname=findViewById(R.id.textView2);
        EditText editTextmessage=findViewById(R.id.editText13);

        batch_name=textViewname.getText().toString();
        message=editTextmessage.getText().toString();

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        date = df.format(Calendar.getInstance().getTime());


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");
        final DatabaseReference reference=referenceuser.push();

        Messages b=new Messages(batch_name,message,date,getEmail());
        reference.setValue(b);

        reference.child("batch_name").setPriority(1);
        reference.child("message").setPriority(2);

        ChildEventListener childEventListener=new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // change_value(batch_name,message,date);
                //clearRecyclerView();
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE){
//                    NotificationChannel notificationChannel=new NotificationChannel("My notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
//                    NotificationManager notificationManager=getSystemService(NotificationManager.class);
//                    notificationManager.createNotificationChannel(notificationChannel);
//
//                }
//
//                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//
//                if(firebaseUser != null){
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Massage_view.this,"My notification")
//                            .setSmallIcon(R.drawable.images1)
//                            .setContentTitle("Student Protal")
//                            .setContentText(firebaseUser.getUid())
//                            .setAutoCancel(true);
//
//                    NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Massage_view.this);
//                    managerCompat.notify(1,builder.build());
//                }else{
//
//                }


                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference referenceuser=firebaseDatabase.getReference("Tokens");
                ValueEventListener li=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterable<DataSnapshot> childSnapshots=snapshot.getChildren();

                        for(DataSnapshot c:childSnapshots){
                            Token_details t=c.getValue(Token_details.class);
                            if(t.getBatch().equals(batch_name)){
                                String token=t.getToken();

                                System.out.println(token+"-----------------------------------");

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
referenceuser.addValueEventListener(li);


                loadPastMassages();


                System.out.println("chield added");

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //change_value(batch_name,message,date);

                System.out.println("Chield added on chield change");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("child onChildRemoved");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // change_value(batch_name,message,date);
                System.out.println("child onChildMoved");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("child onCancelled");
            }
        };

        referenceuser.addChildEventListener(childEventListener);
        Toast toast=Toast.makeText(this,"Your Message send sucessful",Toast.LENGTH_SHORT);
        toast.show();

        editTextmessage.setText("");
        //clearRecyclerView();
        loadPastMassages();

    final String msg=message;


    }

    private  void sendNotification(String recever, String username,String message){
//        DatabaseReference tokens=FirebaseDatabase.getInstance().getReference("Tokens");
       // Query query= tokens.orderByKey().equalTo(recever);
        System.out.println(username+"=========== "+message);
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
