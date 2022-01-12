package com.example.project_protal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.project_protal.time_and_date.diolog1;
import com.example.project_protal.time_and_date.diolog2;
import com.example.project_protal.time_and_date.setEndtime;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_timetable extends AppCompatActivity {
    public static TextView editText;
    public static TextView editText1;
    public static TextView editText3;
    boolean a=true;
    String validID="";
    String batchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);
        Intent i=getIntent();
        batchName=i.getStringExtra("name");
        TextView t=findViewById(R.id.title);

        t.setText(batchName);





//Date
//      editText=findViewById(R.id.editText6);
//        Button date=findViewById(R.id.button18);
//        date.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v) {
//                Calendar myCalendar = Calendar.getInstance();
//
//                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//
//                        editText.setText(year+"-"+month+"-"+ dayOfMonth);
//                    }
//                };
//                DatePickerDialog d = new DatePickerDialog(Add_timetable.this,
//                        listener,
//                        myCalendar.get(Calendar.YEAR),
//                        myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH));
//                d.show();
//            }
//        });


    }



    public void viewDate(View view){
      editText=findViewById(R.id.editText6);
      editText.setText("");
        diolog1 diolog2=new diolog1();
        FragmentManager fm=getSupportFragmentManager();
        diolog2.show(fm,"date1");
    }

    public  void viewTime(View view){
        editText1=findViewById(R.id.editText11);

        diolog2 diolog1=new diolog2();
        FragmentManager fm=getSupportFragmentManager();
        diolog1.show(fm,"time1");
    }

    public  void EndTime(View view){
        editText3=findViewById(R.id.editText12);

        setEndtime diolog1=new setEndtime();
        FragmentManager fm=getSupportFragmentManager();
        diolog1.show(fm,"time2");
    }

//    public  void starttime(View view){
//        TimePickerDialog.OnTimeSetListener lis=new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                EditText editText=findViewById(R.id.editText11);
//                editText.setText(hourOfDay+":"+minute);
//            }
//        };
//        TimePickerDialog tp=new TimePickerDialog(this,lis,13,20,false);
//        tp.show();
//    }

    public  void endttime(View view){
        final Toast toast=Toast.makeText(this,"Class start time not passed..",Toast.LENGTH_SHORT);
        TimePickerDialog.OnTimeSetListener lis=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView editTexttime=findViewById(R.id.editText12);
               // editTexttime.setText(hourOfDay+":"+minute);
                String date=editText.getText().toString();
                String selectedStartTime=editText1.getText().toString();
                SimpleDateFormat sp=new SimpleDateFormat("dd/MM/yyyy");
                Date d=new Date(date);
                String selectedDate=sp.format(d);

                System.out.println(selectedDate);
                String checkStartTime=selectedDate+""+selectedStartTime;



                if(minute<10){
                //  editTexttime.setText(hourOfDay+":0"+minute+":00");
                    String checkEndTime=selectedDate+""+hourOfDay+":0"+minute+":00";

                    try {
                        Date start=new SimpleDateFormat("dd/MM/yyyy").parse(checkStartTime);
                        Date end=new SimpleDateFormat("dd/MM/yyyy").parse(checkEndTime);

                        if(start.before(end)){
                            editTexttime.setText(hourOfDay+":0"+minute+":00");
                        }else{
                            System.out.println("Can not add time");
                            toast.show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{

                    System.out.println(selectedDate+""+hourOfDay+":"+minute+":00");
                    String checkEndTime=selectedDate+""+hourOfDay+":"+minute+":00";

                    try {
                        Date start=new SimpleDateFormat("dd/MM/yyyy").parse(checkStartTime);
                        Date end=new SimpleDateFormat("dd/MM/yyyy").parse(checkEndTime);

                        if(start.before(end)){

                            editTexttime.setText(hourOfDay+":"+minute+":00");
                        }else{
                            System.out.println("Can not add time");

                            toast.show();

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }



            }
        };
        TimePickerDialog tp=new TimePickerDialog(this,lis,13,20,false);
        tp.show();
    }

    boolean ab=false;


    public  void save(View view){
        TextView textView=findViewById(R.id.title);
        TextView editTextdate=findViewById(R.id.editText6);
        EditText editTextdis=findViewById(R.id.editText10);
        TextView editTextstart=findViewById(R.id.editText11);
        TextView editTextend=findViewById(R.id.editText12);

       final String title=textView.getText().toString();
       final String date=editTextdate.getText().toString();
        final String discription=editTextdis.getText().toString();
       final String startTime=editTextstart.getText().toString();
       final String endTime=editTextend.getText().toString();

       if(date.equals("")||date.equals(null) && discription.equals("") || discription.equals(null) && startTime.equals("")||startTime.equals(null) && endTime.equals("")||endTime.equals(null)){
           Toast toast=Toast.makeText(this,"Fill the from first",Toast.LENGTH_SHORT);
           toast.show();
       }else{

           DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
           Query query = reference
                   .child("Time_table")
                   .orderByChild("date")
                   .equalTo(date);
           query.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.getChildrenCount()>0) {
                   if(dataSnapshot.exists()) {

                       Toast toast=Toast.makeText(getApplicationContext(),"You already have a scheduled class on this date. Please select another date.",Toast.LENGTH_SHORT);
                       toast.show();

                   }else{


                       FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                       DatabaseReference referenceuser=firebaseDatabase.getReference("Time_table");
                       DatabaseReference reference=referenceuser.push();
                       Timetable_details ttd=new Timetable_details(title,date,startTime,endTime,discription);
                       reference.setValue(ttd);

                       reference.child("batch").setPriority(1);
                       reference.child("date").setPriority(2);
                       reference.child("discription").setPriority(3);
                       reference.child("startTime").setPriority(4);
                       reference.child("endTime").setPriority(5);

                       ChildEventListener childEventListener=new ChildEventListener() {
                           @RequiresApi(api = Build.VERSION_CODES.O)
                           @Override
                           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




//                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
//                           NotificationChannel notificationChannel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
//                           NotificationManager notificationManager = getSystemService(NotificationManager.class);
//                           notificationManager.createNotificationChannel(notificationChannel);
//
//                       }
//
//
//                       NotificationCompat.Builder builder = new NotificationCompat.Builder(Add_timetable.this, "My notification")
//                               .setSmallIcon(R.drawable.ic_calendar)
//                               .setContentTitle("Student Protal Time Table Update")
//                               .setContentText(discription)
//                               .setAutoCancel(true);
//
//                       NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Add_timetable.this);
//                       managerCompat.notify(1, builder.build());



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
                       Toast toast=Toast.makeText(getApplicationContext()," Sucessfully add new class schedule ",Toast.LENGTH_SHORT);
                       toast.show();



                   }

               }
               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });
         //  String id=validate(title,date,startTime,endTime);
          //     if("2".equals(validate(title,date,startTime,endTime))){

           editTextdate.setText("");
           editTextdis.setText("");
           editTextstart.setText("");
           editTextend.setText("");







       }
    }
    public void viewtime(View view){
        TextView t=findViewById(R.id.title);
        String value=t.getText().toString();
        Intent i=new Intent(this,Time_table_view.class);
        i.putExtra("batch",value);
        i.putExtra("type","Admin");
        startActivity(i);

    }


    public boolean validate(final String title, final String date1, final String startTime, String endTime){




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child("Time_table")
                .orderByChild("date")
                .equalTo(date1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.getChildrenCount()>0) {
                if(dataSnapshot.exists()) {
ab=false;


                }else{

                ab=true;

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return ab;
    }

    public static final boolean isBetweenValidTime(Date startTime, Date endTime, Date validateTime)
    {
        boolean validTimeFlag = false;

        if(endTime.compareTo(startTime) <= 0)
        {
            if(validateTime.compareTo(endTime) < 0 || validateTime.compareTo(startTime) >= 0)
            {
                validTimeFlag = true;
            }
        }
        else if(validateTime.compareTo(endTime) < 0 && validateTime.compareTo(startTime) >= 0)
        {
            validTimeFlag = true;
        }

        return validTimeFlag;
    }
}
