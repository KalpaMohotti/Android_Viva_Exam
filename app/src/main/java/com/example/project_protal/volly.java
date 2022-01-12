package com.example.project_protal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class volly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly);

    }

    public  void sendVollyrequest(View view){
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        String url="http://192.168.1.6/Student_protal/getData";

        Response.Listener rlistener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView textView=findViewById(R.id.textView34);
                textView.setText(response);
            }
        };

        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView textView=findViewById(R.id.textView34);
                textView.setText("Error in request");
                System.out.println(error);
            }
        };
        StringRequest request=new StringRequest(Request.Method.GET,url,rlistener,errorListener);

        requestQueue.add(request);

    }
}
