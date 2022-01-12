package com.example.project_protal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class No_connection extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
Button b=findViewById(R.id.button3);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

if(isOnline(No_connection.this)){
    Intent intent=new Intent(No_connection.this,Start_anim.class);
    startActivity(intent);
}else{
    Toast toast = Toast.makeText(No_connection.this, "No internet connection", Toast.LENGTH_LONG);
    toast.show();
}
    }
});

    }
    private boolean isOnline(No_connection context) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
NetworkInfo wifiCon=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
NetworkInfo mobileCon=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

if(wifiCon != null && wifiCon.isConnected() || (mobileCon !=null && mobileCon.isConnected())){
    return true;
}else{
    return false;
}

    }

}
