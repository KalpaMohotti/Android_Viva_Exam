package com.example.project_protal;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.project_protal.time_and_date.diolog1;

public class timeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_test);
    }
    public void viewDateq(View view){
        EditText text=findViewById(R.id.editText6);
        diolog1 diolog2=new diolog1();
        FragmentManager fm=getSupportFragmentManager();
        diolog2.show(fm,"date1");
    }
}
