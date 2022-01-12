package com.example.project_protal.time_and_date;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.widget.TextView;
import android.widget.TimePicker;

import com.example.project_protal.Add_timetable;

public class diolog2 extends DialogFragment {

    TextView textView;

//    public diolog2(TextView textView){
//       this.textView=textView;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Calendar calendar=Calendar.getInstance();
                //  calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                //  calendar.set(Calendar.MINUTE,minute);

                //SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm: a");
                //String time=

                System.out.println(hourOfDay);
                System.out.println(minute);
                System.out.println();
               // textView.setText(hourOfDay+" : "+minute);

                if(minute<10){
                    Add_timetable.editText1.setText(hourOfDay+":0"+minute+":00");
                }else{
                    Add_timetable.editText1.setText(hourOfDay+":"+minute+":00");
                }
            }
        };

        TimePickerDialog time=new TimePickerDialog(getContext(),listener,13,20,false);

        return time;


    }
}
