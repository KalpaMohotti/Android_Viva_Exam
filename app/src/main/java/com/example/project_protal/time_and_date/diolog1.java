package com.example.project_protal.time_and_date;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;


import com.example.project_protal.Add_timetable;
import com.example.project_protal.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class diolog1 extends DialogFragment {

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

                SimpleDateFormat date=new SimpleDateFormat("dd-MMM-yyyy",Locale.getDefault());
                String selectedDate=date.format(calendar.getTime());




                SimpleDateFormat cdate=new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                Date date1=new Date();
                String currentDate=cdate.format(date1);

                if(checkdate(currentDate,selectedDate)){
                    Add_timetable.editText.setText(selectedDate);
                }else{
                    Toast t=Toast.makeText(getContext(), "You Selected Date already passed..",Toast.LENGTH_SHORT);
               t.show();
                }



            }
        };



        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),listener,2021,7,10);
        return datePickerDialog;
    }

    public boolean checkdate(String CurDate,String SelDate){
        System.out.println(CurDate+ "  "+ SelDate);
Date curDate=new Date(CurDate);
Date selDate=new Date(SelDate);

if(curDate.before(selDate)){
return true;
}
        System.out.println(CurDate+"  "+SelDate);
        return false;
    }



}
