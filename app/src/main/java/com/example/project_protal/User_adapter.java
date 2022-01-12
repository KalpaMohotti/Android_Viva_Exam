package com.example.project_protal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class User_adapter extends RecyclerView.Adapter {
    ArrayList<User_object_data> userlist;
    Context context;
    public  User_adapter(ArrayList<User_object_data> userlist){
        this.userlist=userlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.student_object,viewGroup,false);
        User_view_holder holder=new User_view_holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final User_object_data user=userlist.get(i);
        User_view_holder user_view_holder=(User_view_holder)viewHolder;
        user_view_holder.textView1.setText(user.getBatch());
        user_view_holder.textView2.setText(user.getFname()+" "+user.getLname());
        user_view_holder.textView3.setText(user.getEmail());


        user_view_holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.EMPTY.parse("mailto:" + user.getEmail()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Student Protal");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    context.startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
                }
            }
        );

        user_view_holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,user.getFname(),Toast.LENGTH_SHORT).show();
                String email=user.getEmail();


                Intent i=new Intent(context,View_profile.class);
                i.putExtra("email",email);

               // i.putExtra("type","viewUser");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }



}
