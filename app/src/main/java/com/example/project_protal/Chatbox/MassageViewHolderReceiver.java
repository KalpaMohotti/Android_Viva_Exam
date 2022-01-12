package com.example.project_protal.Chatbox;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.project_protal.R;

public class MassageViewHolderReceiver extends RecyclerView.ViewHolder {
    TextView Rmassage;
    public MassageViewHolderReceiver(View view) {
        super(view);
        Rmassage= view.findViewById(R.id.textView38);

    }
}
