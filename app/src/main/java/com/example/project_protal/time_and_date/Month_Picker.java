package com.example.project_protal.time_and_date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.project_protal.Add_timetable;
import com.example.project_protal.Pay_monthly_fee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Month_Picker extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                System.out.println(year);
                System.out.println(month);
                System.out.println(dayOfMonth);

                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DATE,dayOfMonth);

                SimpleDateFormat date=new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
                String selectedDate=date.format(calendar.getTime());




                    Pay_monthly_fee.textView001.setText(selectedDate);




            }
        };



        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),listener,2021,2,10);
        return datePickerDialog;
    }
}
