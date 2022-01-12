package com.example.project_protal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.project_protal.Object_classes.ChartDetails;
import com.example.project_protal.Object_classes.Fees_Details;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class student_chart extends AppCompatActivity {

    int b=4;
    int cc=0;
    BarChart barChart;
    ArrayList<ChartDetails> chartDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_chart);

chartDetails=new ArrayList<>();




//getEntries();
//        BarChart barChart=findViewById(R.id.chart);
//
//        ArrayList<BarEntry> entrys=new ArrayList<>();
//        BarEntry entry1=new BarEntry(0,70);
//        entrys.add(entry1);
//
//        BarEntry entry2=new BarEntry(10,30);
//        entrys.add(entry2);
//
//        BarEntry entry3=new BarEntry(20,100);
//        entrys.add(entry3);
//
//        BarEntry entry4=new BarEntry(30,80);
//        entrys.add(entry4);
//
//        BarEntry entry5=new BarEntry(40,90);
//        entrys.add(entry5);
//
//        BarEntry entry6=new BarEntry(50,80);
//        entrys.add(entry6);
//
//        BarEntry entry7=new BarEntry(60,50);
//        entrys.add(entry7);
//
//        BarDataSet dataSet=new BarDataSet(entrys,"weekly sales");
//        BarData data=new BarData(dataSet);
//        barChart.setData(data);
//
//        barChart.getDescription().setEnabled(false); //Chart eke description eka remove karnwa.
//        data.setBarWidth(8); //Bar eke width eka change krnwa..
//        barChart.setFitBars(true); //Chart eke ida athule pamanak bars tika create wei.
//
//        barChart.setPinchZoom(false);  //Chart eka zoom wen ne na...
//        barChart.setScaleXEnabled(false);
//        barChart.setScaleYEnabled(false);
//
//        ArrayList<Integer> colours=new ArrayList<>();     //bars walata colours add kirima..
//        colours.add(getResources().getColor(R.color.c1));
//        colours.add(getResources().getColor(R.color.c2));
//        colours.add(getResources().getColor(R.color.c3));
//        colours.add(getResources().getColor(R.color.c4));
//        colours.add(getResources().getColor(R.color.c5));
//        colours.add(getResources().getColor(R.color.c6));
//        colours.add(getResources().getColor(R.color.c7));
//
//        dataSet.setColors(colours);
//
//
//        ArrayList<LegendEntry> legends=new ArrayList<>();  //  Bar ekta discription ekk add kirima..
//        LegendEntry legend1=new LegendEntry("Monday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(0));
//        LegendEntry legend2=new LegendEntry("The",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(1));
//        LegendEntry legend3=new LegendEntry("Wen",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(2));
//        LegendEntry legend4=new LegendEntry("Thersday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(3));
//        LegendEntry legend5=new LegendEntry("Fri",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(4));
//        LegendEntry legend6=new LegendEntry("Saterday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(5));
//        LegendEntry legend7=new LegendEntry("Sunday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(6));
//
//        Legend legend=barChart.getLegend();
//        legends.add(legend1);
//        legends.add(legend2);
//        legends.add(legend3);
//        legends.add(legend4);
//        legends.add(legend5);
//        legends.add(legend6);
//        legends.add(legend7);
//        legend.setCustom(legends);
//
//
//
//
//        barChart.animateY(2000, Easing.EaseInCubic); //add animation into bars.
//
//
//
//
//        barChart.invalidate();
getEntries();
    }








//    int x=0;
//   // BarData data;
//   // BarDataSet dataSet;
//    private void getEntries() {
//        barChart = findViewById(R.id.chart);
//
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference referenceuser=firebaseDatabase.getReference("Fees");
//        ValueEventListener listener=new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> childSnapshots = dataSnapshot.getChildren();
//               ArrayList<BarEntry> entrys = new ArrayList<>();
//
//                for (DataSnapshot c : childSnapshots) {
//
//                    Fees_Details ud = c.getValue(Fees_Details.class);
//                    System.out.println(ud.getBatch());
//
//
//                    BarEntry entry1 = new BarEntry(x,(float) Double.parseDouble(ud.getPayment()+""));
//                    entrys.add(entry1);
//                    x=x+10;
//                 //    dataSet = new BarDataSet(entrys, "Students");
//               //   data = new BarData(dataSet);
//                 //   barChart.setData(data);
//                }
//
//
//
//
//
//
//
//
////                    BarEntry entry2 = new BarEntry(x, 1000);
////                    entrys.add(entry2);
//
//                        BarDataSet dataSet = new BarDataSet(entrys, "Students");
//                        BarData data = new BarData(dataSet);
//                        barChart.setData(data);
//
//                        barChart.getDescription().setEnabled(false); //Chart eke description eka remove karnwa.
//                        data.setBarWidth(8); //Bar eke width eka change krnwa..
//                        barChart.setFitBars(true); //Chart eke ida athule pamanak bars tika create wei.
//
//                        barChart.setPinchZoom(false);  //Chart eka zoom wen ne na...
//                        barChart.setScaleXEnabled(false);
//                        barChart.setScaleYEnabled(false);
//
//                        ArrayList<Integer> colours = new ArrayList<>();     //bars walata colours add kirima..
//                        colours.add(getResources().getColor(R.color.c1));
//                    colours.add(getResources().getColor(R.color.c3));
//                    colours.add(getResources().getColor(R.color.c4));
////                    colours.add(getResources().getColor(R.color.c4));
////                    colours.add(getResources().getColor(R.color.c5));
////                    colours.add(getResources().getColor(R.color.c6));
////                    colours.add(getResources().getColor(R.color.c7));
//
//                        dataSet.setColors(colours);
//
//
//                        ArrayList<LegendEntry> legends = new ArrayList<>();  //  Bar ekta discription ekk add kirima..
//                        LegendEntry legend1 = new LegendEntry("Students", Legend.LegendForm.CIRCLE, Float.NaN, Float.NaN, null, colours.get(0));
//              // LegendEntry legend2 = new LegendEntry("Students", Legend.LegendForm.CIRCLE, Float.NaN, Float.NaN, null, colours.get(0));
//                 //  LegendEntry legend2=new LegendEntry("The",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(1));
//                   // LegendEntry legend3=new LegendEntry("Wen",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(2));
//                   // LegendEntry legend4=new LegendEntry("Thersday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(3));
//                   // LegendEntry legend5=new LegendEntry("Fri",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(4));
////                    LegendEntry legend6=new LegendEntry("Saterday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(5));
////                    LegendEntry legend7=new LegendEntry("Sunday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(6));
//
//                        Legend legend = barChart.getLegend();
//                        legends.add(legend1);
//             //       legends.add(legend2);
//           //         legends.add(legend3);
////                    legends.add(legend4);
////                    legends.add(legend5);
////                    legends.add(legend6);
////                    legends.add(legend7);
//                        legend.setCustom(legends);
//
//
//
//
//
//                        barChart.animateY(2000, Easing.EaseInCubic); //add animation into bars.
//
//
//                        barChart.invalidate();
//
//
//
//
//
//
//           }
//
//            @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        referenceuser.addListenerForSingleValueEvent(listener);
//
//
//
//    }


    int x=0;
    // BarData data;
    // BarDataSet dataSet;
    private void getEntries() {
        barChart = findViewById(R.id.chart);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("Fees");
        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots = dataSnapshot.getChildren();
                ArrayList<BarEntry> entrys = new ArrayList<>();

                ArrayList<Integer> colours = new ArrayList<>();

                LegendEntry legend1;
                for (DataSnapshot c : childSnapshots) {

                    Fees_Details ud = c.getValue(Fees_Details.class);
                    System.out.println(ud.getBatch());


                    BarEntry entry1 = new BarEntry(x,(float) Double.parseDouble(ud.getPayment()+""));
                    entrys.add(entry1);



//                    BarEntry entry2 = new BarEntry(x, 1000);
//                    entrys.add(entry2);

                    BarDataSet dataSet = new BarDataSet(entrys, "Students");
                    BarData data = new BarData(dataSet);
                    barChart.setData(data);

                    barChart.getDescription().setEnabled(false); //Chart eke description eka remove karnwa.
                    data.setBarWidth(8); //Bar eke width eka change krnwa..
                    barChart.setFitBars(true); //Chart eke ida athule pamanak bars tika create wei.

                    barChart.setPinchZoom(false);  //Chart eka zoom wen ne na...
                    barChart.setScaleXEnabled(false);
                    barChart.setScaleYEnabled(false);

                       //bars walata colours add kirima..
                    colours.add(getResources().getColor(R.color.c1));
                    colours.add(getResources().getColor(R.color.c3));
                    colours.add(getResources().getColor(R.color.c4));
//                    colours.add(getResources().getColor(R.color.c4));
//                    colours.add(getResources().getColor(R.color.c5));
//                    colours.add(getResources().getColor(R.color.c6));
//                    colours.add(getResources().getColor(R.color.c7));

                    dataSet.setColors(colours);

                    ArrayList<LegendEntry> legends=new ArrayList<>();
                     //  Bar ekta discription ekk add kirima..
                     legend1 = new LegendEntry("A/L Science 2018-2020", Legend.LegendForm.CIRCLE, Float.NaN, Float.NaN, null, colours.get(0));
                     LegendEntry legend2 = new LegendEntry("A/L Science 2020-2022", Legend.LegendForm.CIRCLE, Float.NaN, Float.NaN, null, colours.get(1));
                    //  LegendEntry legend2=new LegendEntry("The",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(1));
                    // LegendEntry legend3=new LegendEntry("Wen",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(2));
                    // LegendEntry legend4=new LegendEntry("Thersday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(3));
                    // LegendEntry legend5=new LegendEntry("Fri",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(4));
//                    LegendEntry legend6=new LegendEntry("Saterday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(5));
//                    LegendEntry legend7=new LegendEntry("Sunday",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,colours.get(6));


                    legends.add(legend1);
                    legends.add(legend2);
                    Legend legend = barChart.getLegend();
                    legend.setCustom(legends);
              //             legends.add(legend2);
                    //         legends.add(legend3);
//                    legends.add(legend4);
//                    legends.add(legend5);
//                    legends.add(legend6);
//                    legends.add(legend7);



                    x=x+10;
                    //    dataSet = new BarDataSet(entrys, "Students");
                    //   data = new BarData(dataSet);
                    //   barChart.setData(data);
                }








//                    BarEntry entry2 = new BarEntry(x, 1000);
//                    entrys.add(entry2);







                barChart.animateY(2000, Easing.EaseInCubic); //add animation into bars.


                barChart.invalidate();






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        referenceuser.addListenerForSingleValueEvent(listener);



    }








}
