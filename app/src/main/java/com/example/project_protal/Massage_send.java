package com.example.project_protal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class Massage_send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage_send);
        setName();
        m();
        final ScrollView scv=findViewById(R.id.scorll_msg);
        //   scv.fullScroll(ScrollView.FOCUS_DOWN);
        scv.postDelayed(new Runnable() {
            @Override
            public void run() {
                scv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 600);





//        final TextView messagetext=findViewById(R.id.massageText);
//        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.relaytive_layout);
//        linearLayout.removeAllViews();
//
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");
//
//          referenceuser.addValueEventListener(new ValueEventListener() {
//
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
//       // linearLayout.removeAllViews();
//        for(DataSnapshot c:childSnapshots){
//            Messages user= c.getValue(Messages.class);
//            //System.out.println(user.getBatch_name());
//            //System.out.println(batchtextView());
//            if(user.getBatch_name().equals(batchtextView())){
//                if(user.getEmail().equals(getEmail())){
//
//
//                }else{
//                    load2nd(user.getMessage(),user.getDate(),user.getEmail());
//
//
//                }
//
//
//            }
//
//        }
//
//
//
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }
//
//});


    }
    public void setName(){
        Intent i=getIntent();
        String name= i.getStringExtra("name");
        TextView ed=findViewById(R.id.Batch_name);
        ed.setText(name);

    }



    String date;
    String batch_name;
    String message;
    public void Send_message(View view){
       try {
           TextView textViewname=findViewById(R.id.Batch_name);
           EditText editTextmessage=findViewById(R.id.massageText);

           batch_name=textViewname.getText().toString();
           message=editTextmessage.getText().toString();

           DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
           date = df.format(Calendar.getInstance().getTime());
           System.out.println(date);
           System.out.println("batch name error  "+batch_name);
           System.out.println(message);


           FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
           DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");
           DatabaseReference reference=referenceuser.push();

           Messages b=new Messages(batch_name,message,date,getEmail());
           reference.setValue(b);

           reference.child("batch_name").setPriority(1);
           reference.child("message").setPriority(2);
           reference.child("email").setPriority(3);

           ChildEventListener childEventListener=new ChildEventListener() {
               @Override
               public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   // m();
                   String value=dataSnapshot.getValue().toString();
                   System.out.println(value+" value is there");

               }

               @Override
               public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   // m();
                   String value=dataSnapshot.getValue().toString();
                   System.out.println(value+" value is there");
               }

               @Override
               public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                   String value=dataSnapshot.getValue().toString();
                   System.out.println(value+" value is there");
               }

               @Override
               public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   System.out.println("add3");
                   String value=dataSnapshot.getValue().toString();
                   System.out.println(value+" value is there");
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           };

           reference.addChildEventListener(childEventListener);
           Toast toast=Toast.makeText(this,"Your Message send sucessful",Toast.LENGTH_SHORT);
           toast.show();
           editTextmessage.setText("");
           m();
           final ScrollView scv=findViewById(R.id.scorll_msg);
           //   scv.fullScroll(ScrollView.FOCUS_DOWN);
           scv.postDelayed(new Runnable() {
               @Override
               public void run() {
                   scv.fullScroll(ScrollView.FOCUS_DOWN);
               }
           }, 600);


////Add fragment and data
//           EditText t=findViewById(R.id.massageText);
//
//           String text=t.getText().toString();
//
//
//           Bundle bundle = new Bundle();
//           bundle.putString("text", text);
//
//
//// set Fragmentclass Arguments
//
//
//
//
//           Chat_fragment fragment1=new Chat_fragment();
//           fragment1.setArguments(bundle);
//
//
//           FragmentManager manager=getSupportFragmentManager();
//           FragmentTransaction transaction=manager.beginTransaction();
//           transaction.add(R.id.relaytive_layout,fragment1,"f1");
//           transaction.commit();




       }catch (Exception e){
           e.printStackTrace();
       }

    }
    public String batchtextView(){
         TextView textViewbatch=findViewById(R.id.Batch_name);
         String batch=textViewbatch.getText().toString();
        return batch;
    }
    public void m(){
        final TextView messagetext=findViewById(R.id.massageText);
        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.relaytive_layout);
            linearLayout.removeAllViews();

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Messages");


        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    Messages user= c.getValue(Messages.class);
                    System.out.println(user.getBatch_name());
                    //System.out.println(batchtextView());
                    if(user.getBatch_name().equals(batchtextView())){
                        if(user.getEmail().equals(getEmail())){
                            load(user.getMessage(),user.getDate());

                        }else{
                            load2nd(user.getMessage(),user.getDate(),user.getEmail());

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

    public void load(String massage,String date){
        EditText t=findViewById(R.id.massageText);

        //String text=t.getText().toString();


        Bundle bundle = new Bundle();
        bundle.putString("text", massage);
        bundle.putString("date",date);


// set Fragmentclass Arguments




        Chat_fragment fragment1=new Chat_fragment();
        fragment1.setArguments(bundle);


        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.relaytive_layout,fragment1,"f1");
        transaction.commit();
    }

    public void load2nd(String massage,String date,String email){
        EditText t=findViewById(R.id.massageText);

        //String text=t.getText().toString();


        Bundle bundle = new Bundle();
        bundle.putString("text", massage);
        bundle.putString("date",date);
        bundle.putString("email",email);

// set Fragmentclass Arguments




        Chat_fragment2 fragment1=new Chat_fragment2();
        fragment1.setArguments(bundle);


        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.relaytive_layout,fragment1,"f2");
        transaction.commit();
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
