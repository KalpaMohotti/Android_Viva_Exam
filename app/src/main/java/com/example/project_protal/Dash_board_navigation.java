package com.example.project_protal;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class Dash_board_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_navigation);

        Intent i=getIntent();
//        if(i!=null){
//            if(i.getStringExtra("type").equals("Student")){
//            LinearLayout l1=(LinearLayout) findViewById(R.id.register_batch);
//            l1.setVisibility(View.GONE);
//            }
//        }


        getdata();
        LinearLayout c=(LinearLayout) findViewById(R.id.Chat);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Select_batch.class);
                i.putExtra("name","chat");
                startActivity(i);
            }
        });

        LinearLayout c1=(LinearLayout) findViewById(R.id.search);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(), com.example.project_protal.View.class);
                startActivity(ii);
            }
        });

        LinearLayout c2=(LinearLayout) findViewById(R.id.register_batch);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(getApplicationContext(), Register_batch.class);
                startActivity(iii);
            }
        });

        LinearLayout c3=(LinearLayout)findViewById(R.id.timetable);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Select_batch.class);
                i.putExtra("name","timetable");
                startActivity(i);
            }
        });

        LinearLayout c4=(LinearLayout)findViewById(R.id.view_chart);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),student_chart.class);

                startActivity(i);
            }
        });



        LinearLayout c5=(LinearLayout)findViewById(R.id.manage_fees);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Manage_Fees.class);

                startActivity(i);
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send Massages", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i=new Intent(getApplicationContext(),Select_batch.class);
                i.putExtra("name","chat");
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


Setvalues();
loadimage();

    }




    public void loadimage(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
       final ImageView img=headerView.findViewById(R.id.imageView);
        TextView subTitle = headerView.findViewById(R.id.nav_email);
        String email=subTitle.getText().toString();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
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
        TextView title = headerView.findViewById(R.id.Nav_textView_name);
        TextView subTitle = headerView.findViewById(R.id.nav_email);
        title.setText(getdataName());
        subTitle.setText(getdataEmail());
    }

int counter=0;
    @Override
    public void onBackPressed() {
        counter++;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
         //   super.onBackPressed();
            this.finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board_navigation, menu);
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


    public  void removeSharedPreferancesLogout(){
        SharedPreferences s=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor e=s.edit();
        e.clear();
        e.apply();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            viewProfile();
        } else if (id == R.id.nav_tools) {

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
        TextView subTitle = headerView.findViewById(R.id.nav_email);
        String email=subTitle.getText().toString();
        Intent i=new Intent(this,View_profile.class);
        i.putExtra("email",email);
        startActivity(i);
    }

    public  void getdata(){

        String name="name";


        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(name,"not found");


        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()) {
          //  Toast toast = Toast.makeText(this, e.getKey() + " " + e.getValue(), Toast.LENGTH_SHORT);
         //   toast.show();
            // String e=s.getString("name","not found");
            TextView t = findViewById(R.id.dash);
            if(e.getKey().equals("name")){
                t.setText(e.getValue().toString());
            }else{
                System.out.println("No matching key");
            }

        }
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
