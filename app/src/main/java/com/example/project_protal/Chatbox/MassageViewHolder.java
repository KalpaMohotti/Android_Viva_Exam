package com.example.project_protal.Chatbox;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.project_protal.R;

public class MassageViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    TextView textView2;
    public MassageViewHolder(View View) {
        super(View);

        textView= View.findViewById(R.id.textView37);
        textView2=View.findViewById(R.id.textView39);

    }
}
