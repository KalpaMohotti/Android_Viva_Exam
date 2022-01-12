package com.example.project_protal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class User_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String batch="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navigation);


        Intent i=getIntent();
        String a=i.getStringExtra("uname");
        String b=i.getStringExtra("type");
        TextView t=findViewById(R.id.dash);
        // TextView type=findViewById(R.id.type);
        // type.setText(b);
        // t.setText(a);
        t.setText(getUsername());



        TextView text=findViewById(R.id.textView5);
        final String batchname=text.getText().toString();
        System.out.println("batch_name_print    --"+textViewget() );
        LinearLayout c=(LinearLayout) findViewById(R.id.Chat);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Massage_view.class);
                i.putExtra("name",textViewget());

                startActivity(i);
            }
        });


        LinearLayout c3=(LinearLayout)findViewById(R.id.timetable);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Time_table_view.class);
                i.putExtra("type","Student");
                i.putExtra("batch",textViewget());
                startActivity(i);
            }
        });

        LinearLayout location=(LinearLayout)findViewById(R.id.location_class);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MapsActivity2.class);
                startActivity(i);
            }
        });

        LinearLayout pay=(LinearLayout)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Pay_monthly_fee.class);
                i.putExtra("batch",textViewget());
                        i.putExtra("email",getdata());
                startActivity(i);
            }
        });

        max();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send Massages", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i=new Intent(getApplicationContext(),Massage_view.class);
                i.putExtra("name",textViewget());

                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        NavigationView item=findViewById(R.id.action_settings);
//        item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              removeSharedPreferances();
//
//            }
//        });
        Setvalues();
        loadimage();
    }
    public void loadimage(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final ImageView img=headerView.findViewById(R.id.imageView);
        TextView subTitle = headerView.findViewById(R.id.nav_userEmail);
        String email=subTitle.getText().toString();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
       // StorageReference photoReference= storageReference.child("images/"+email);
        StorageReference photoReference= storageReference.child("images/"+email);

        final long ONE_MEGABYTE = 1024 * 1024;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                img.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void Setvalues(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView title = headerView.findViewById(R.id.nav_ysername);
        TextView subTitle = headerView.findViewById(R.id.nav_userEmail);
        title.setText(getdataName());
        subTitle.setText(getdataEmail());
    }


    public  void removeSharedPreferancesLogout(){
        SharedPreferences s=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor e=s.edit();
        e.clear();
        e.apply();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void max(){



        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot c:childSnapshots){
                    User_details user= c.getValue(User_details.class);

                    //System.out.println(user.getEmail());
                    // System.out.println(user.getYear());
                    System.out.println(getdata());
                    if(user.getEmail().equals(getdata())){
                        System.out.println(user.getYear()+"1");
                        batch=user.getYear();
                        System.out.println(batch);
                        textViewset();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        referenceuser.addListenerForSingleValueEvent(listener);
        //t.setText(batch);

    }
    public void textViewset(){
        TextView t=findViewById(R.id.textView5);
        t.setText(batch);
    }
    public String textViewget(){
        TextView t=findViewById(R.id.textView5);
        String text=t.getText().toString();
        return text;
    }


    public  String getdata(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            //  System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("email")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }

    public  String getUsername(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            //  System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("name")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            this.finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            removeSharedPreferancesLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_gallery) {
                viewProfile();
        }else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void viewProfile(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView subTitle = headerView.findViewById(R.id.nav_userEmail);
        String email=subTitle.getText().toString();
        Intent i=new Intent(this,View_profile.class);
        i.putExtra("email",email);
        startActivity(i);
    }

    public  String getdataName(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("name")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }

    public  String getdataEmail(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("email")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }
}
