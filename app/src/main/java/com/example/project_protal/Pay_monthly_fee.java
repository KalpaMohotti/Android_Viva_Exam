package com.example.project_protal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_protal.Object_classes.Fees_Details;
import com.example.project_protal.Object_classes.Payment_Details;
import com.example.project_protal.time_and_date.Month_Picker;
import com.example.project_protal.time_and_date.diolog1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.PHResponse;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.Item;
import lk.payhere.androidsdk.model.StatusResponse;

//import lk.payhere.androidsdk.PHConfigs;
//import lk.payhere.androidsdk.PHConstants;
//import lk.payhere.androidsdk.PHMainActivity;
//import lk.payhere.androidsdk.PHResponse;
//import lk.payhere.androidsdk.model.InitRequest;
//import lk.payhere.androidsdk.model.Item;
//import lk.payhere.androidsdk.model.StatusResponse;

public class Pay_monthly_fee extends AppCompatActivity {
    public static TextView textView001;
String batch="";
String email="";
TextView amountpay;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_monthly_fee);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE){
                    NotificationChannel notificationChannel=new NotificationChannel("My notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager notificationManager=getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(notificationChannel);

                }

        Intent i=getIntent();
         batch= i.getStringExtra("batch");
        email=i.getStringExtra("email");
      //  TextView ed=findViewById(R.id.textView2);


    // batch = "A/L Science 2018-2020";
   //  email="kasun7521madushanka@gmail.com";
amountpay=findViewById(R.id.textView52);

        SimpleDateFormat cdate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        Date date1 = new Date();
        String currentDate = cdate.format(date1);

        TextView b = findViewById(R.id.textView50);
        b.setText(batch);
        final TextView f = findViewById(R.id.textView52);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference referenceuser = firebaseDatabase.getReference("Fees");
        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> childSnapshots = snapshot.getChildren();
                for (DataSnapshot c : childSnapshots) {
                    Fees_Details user = c.getValue(Fees_Details.class);
                    if (user.getBatch().equals(batch)) {
                        f.setText(user.getPayment());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);

        final TextView et1 = findViewById(R.id.textView54);
        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView001 = findViewById(R.id.textView54);
                textView001.setText("Select");
                Month_Picker diolog2 = new Month_Picker();
                FragmentManager fm = getSupportFragmentManager();
                diolog2.show(fm, "date1");

            }
        });




        Button pay=findViewById(R.id.button22);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView001.getText().equals("Select")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Select The Month First",Toast.LENGTH_SHORT);
                    toast.show();
                }else{



                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference referenceuser = firebaseDatabase.getReference("User_details");
                    ValueEventListener listener = new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Iterable<DataSnapshot> childSnapshots = snapshot.getChildren();
                            for (DataSnapshot c : childSnapshots) {
                                User_details user = c.getValue(User_details.class);
                                if (user.getEmail().equals(email)) {
                              String email1=user.getEmail();
                              String fname=user.getFname();
                              String lname=user.getLname();
                              String mobile=user.getMobile();


                              pay(email1,fname,lname,mobile,textView001.getText().toString(),amountpay.getText().toString());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    referenceuser.addListenerForSingleValueEvent(listener);
                }
            }
        });
    }

    final static int PAYHERE_REQUEST = 11010;

    public void pay(String email,String fname,String lname,String mobile, String month,String amount) {
       InitRequest req = new InitRequest();
        req.setMerchantId("1213167");       // Your Merchant PayHere ID
        req.setMerchantSecret("4fW1P4ukYRr5tru8LOcHQLvyRn4KDLuokOVZrg5XmU8E"); // Your Merchant secret (Add your app at Settings > Domains & Credentials, to get this))
        req.setCurrency("LKR");             // Currency code LKR/USD/GBP/EUR/AUD
        req.setAmount(Double.parseDouble(amount));             // Final Amount to be charged
        req.setOrderId("230000123");        // Unique Reference ID
        req.setItemsDescription(month+" Monthly Fees");  // Item description title
        req.setCustom1("Thank you for your "+month+" Payment");
        req.setCustom2("");
        req.getCustomer().setFirstName(fname);
        req.getCustomer().setLastName(lname);
        req.getCustomer().setEmail(email);
        req.getCustomer().setPhone(mobile);
        req.getCustomer().getAddress().setAddress("No.1, Galle Road");
        req.getCustomer().getAddress().setCity("Colombo");
        req.getCustomer().getAddress().setCountry("Sri Lanka");

//Optional Params
        req.getCustomer().getDeliveryAddress().setAddress("Java Institute");
        req.getCustomer().getDeliveryAddress().setCity("Kandy");
        req.getCustomer().getDeliveryAddress().setCountry("Sri Lanka");
        req.getItems().add(new Item(null, "Monthly Fees", 1, Double.parseDouble(amount)));

        Intent intent = new Intent(this, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, req);
        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        startActivityForResult(intent, PAYHERE_REQUEST); //unique request ID like private final static int PAYHERE_REQUEST = 11010;




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYHERE_REQUEST && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) {
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT);
            if (resultCode == Activity.RESULT_OK) {
                String msg;
                if (response != null)
                    if (response.isSuccess()){
                        msg = "Activity result:" + response.getData().toString();

                Update_Payment_details();


            }else
                        msg = "Result:" + response.toString();
                else
                    msg = "Result: no response";
               // Log.d(TAG, msg);
               // textView001.setText(msg);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (response != null)
                    textView001.setText("Select");
                else
                    textView001.setText("Select");
            }
        }
    }


    public void Update_Payment_details(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference referenceuser = firebaseDatabase.getReference("User_details");
        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> childSnapshots = snapshot.getChildren();
                for (DataSnapshot c : childSnapshots) {
                    User_details user = c.getValue(User_details.class);

                    if (user.getEmail().equals(email)) {
                        String email1=user.getEmail();
                        String fname=user.getFname();
                        String lname=user.getLname();
                        String mobile=user.getYear();


                        paymentSuccess(email1,fname,lname,mobile,textView001.getText().toString(),amountpay.getText().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);










    }
    public void paymentSuccess(final String email, String Fname, String Lname, String batch, final String Month, final String Amount ){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Payment_Details");
        DatabaseReference reference=referenceuser.push();
        Payment_Details p=new Payment_Details(email,batch,Fname, Lname,Amount, Month);
        reference.setValue(p);

        reference.child("email").setPriority(1);
        reference.child("batch").setPriority(2);
        reference.child("fname").setPriority(3);
        reference.child("lname").setPriority(4);
        reference.child("amount").setPriority(5);
        reference.child("month").setPriority(6);
        ChildEventListener listener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(email.equals(getEmail())){
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Pay_monthly_fee.this,"My notification")
                            .setSmallIcon(R.drawable.notification)
                            .setContentTitle("Student Communication Protal")
                            .setContentText("We have received your "+Month+" payment of Rs "+Amount+". Thank you for your payment.")
                            .setAutoCancel(true);

                    NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Pay_monthly_fee.this);
                    managerCompat.notify(1,builder.build());
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addChildEventListener(listener);

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