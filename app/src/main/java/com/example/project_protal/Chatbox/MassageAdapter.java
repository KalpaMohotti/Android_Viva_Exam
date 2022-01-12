package com.example.project_protal.Chatbox;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_protal.R;

import java.util.ArrayList;

public class MassageAdapter extends RecyclerView.Adapter {

    ArrayList<MassageOb> massagelist;
    Context context;
    String userEmail;

    public  MassageAdapter(ArrayList<MassageOb> massagelist,String userEmail){
        this.massagelist=massagelist;
        this.userEmail=userEmail;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.massage_view,viewGroup,false);
        MassageViewHolder holder=new MassageViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MassageOb massageOb= massagelist.get(i);
        MassageViewHolder massageViewHolder=(MassageViewHolder)viewHolder;



        if(userEmail.equals(massageOb.getmObjemail())){
            massageViewHolder.textView.setText(massageOb.getMassage());
            massageViewHolder.textView2.setVisibility(View.GONE);
        }else{
            massageViewHolder.textView2.setText(massageOb.getMassage());
            massageViewHolder.textView.setVisibility(View.GONE);
        }


      //  System.out.println(massageOb.getMassage());



    }

    @Override
    public int getItemCount() {
        return massagelist.size();
    }



}
