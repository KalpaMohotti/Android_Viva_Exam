package com.example.project_protal.batch_select;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_protal.Add_timetable;
import com.example.project_protal.Massage_view;
import com.example.project_protal.R;

import java.util.ArrayList;

public class Batch_adapter extends RecyclerView.Adapter {
    ArrayList<Batch> userlist;
    Context context;
    String layoutname;
    public Batch_adapter(ArrayList<Batch> userlist,String name){
        this.userlist=userlist;
        this.layoutname=name;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.select_batch,viewGroup,false);
        Batch_view_holder holder=new Batch_view_holder(view);
        return holder;
    }
       // String value="";
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final Batch user=userlist.get(i);
        final Batch_view_holder user_view_holder=(Batch_view_holder)viewHolder;
        user_view_holder.textView.setText(user.getName());
       // value=user.getName();

        user_view_holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(user_view_holder.textView.getText());
                loadintend(user_view_holder.textView.getText().toString());
            }
        });



    }

    @Override
    public int getItemCount() {
       return userlist.size();
    }

    public  void loadintend(String value){
        if(layoutname.equals("chat")){
//            Intent i = new Intent(context.getApplicationContext(), Massage_send.class);
            Intent i=new Intent(context.getApplicationContext(), Massage_view.class);
            i.putExtra("name",value);
            context.getApplicationContext().startActivity(i);
        }else if(layoutname.equals("timetable")){
            Intent i2=new Intent(context.getApplicationContext(), Add_timetable.class);
            i2.putExtra("name",value);

            context.getApplicationContext().startActivity(i2);
        }


    }
}
