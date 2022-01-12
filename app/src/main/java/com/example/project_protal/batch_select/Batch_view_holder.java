package com.example.project_protal.batch_select;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.project_protal.R;

public class Batch_view_holder extends RecyclerView.ViewHolder {
    TextView textView;
   public Batch_view_holder(View v){
       super(v);
       textView= (TextView) v.findViewById(R.id.textView10);

    }
}
