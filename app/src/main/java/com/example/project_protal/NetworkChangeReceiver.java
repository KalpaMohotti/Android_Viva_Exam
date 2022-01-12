package com.example.project_protal;



import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

//    private Context con;
//    public NetworkChangeReceiver(Context context){
//        this.con=context;
//    }


    @Override


    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                System.out.println("Connection success");
                Toast toast = Toast.makeText(context, "Connection Success", Toast.LENGTH_LONG);
                toast.show();


            } else {
                System.out.println("Connection Error");
                //startActivity(con);
                startActivity(context);
                Toast toast = Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG);
                toast.show();


            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, No_connection.class));
    }

}