package com.example.project_protal.Chatbox;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_protal.R;

import java.util.ArrayList;

public class MassageAdapterReceiver extends RecyclerView.Adapter {

    ArrayList<MassageOb> massagelist;
    Context context;
    public  MassageAdapterReceiver(ArrayList<MassageOb> massagelist){
        this.massagelist=massagelist;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.massage_view2,viewGroup,false);
        MassageViewHolderReceiver holder=new MassageViewHolderReceiver(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MassageOb massageOb= massagelist.get(i);
        MassageViewHolderReceiver massageViewHolder=(MassageViewHolderReceiver) viewHolder;
        massageViewHolder.Rmassage.setGravity(Gravity.LEFT);
        massageViewHolder.Rmassage.setText(massageOb.getMassage());
    }

    @Override
    public int getItemCount() {
        return massagelist.size();
    }
}
