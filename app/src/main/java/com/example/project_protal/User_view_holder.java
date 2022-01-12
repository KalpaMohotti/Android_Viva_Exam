package com.example.project_protal;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class User_view_holder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        Button button;
   public User_view_holder(View view){
    super(view);

    textView1=view.findViewById(R.id.textView11);
    textView2=view.findViewById(R.id.textView12);
    textView3=view.findViewById(R.id.textView13);
    button=view.findViewById(R.id.button11);

   }
}
