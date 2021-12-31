package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView prede,text,dates,d1,d2,d3,d4,d5,d6,d7;
    LinearLayout l1,l2,l3,l4,l5,l6,l7;
    LottieAnimationView lottieAnimationView1,lottieAnimationView2,lottieAnimationView3;
    private Calendar calendar;
    LinearLayout lin;
    SimpleDateFormat simpleDateFormat,simpleDateFormat2;
    private String Date,Time;
    List<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prede=findViewById(R.id.predict);
        lottieAnimationView1=findViewById(R.id.lav_actionBar1);
       lottieAnimationView2=findViewById(R.id.lav_actionBar2);
       d1=findViewById(R.id.d1);
        d2=findViewById(R.id.d2);
        d3=findViewById(R.id.d3);
        d4=findViewById(R.id.d4);
        d5=findViewById(R.id.d5);
        d6=findViewById(R.id.d6);
        d7=findViewById(R.id.d7);

        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);
        l5=findViewById(R.id.l5);
        l6=findViewById(R.id.l6);
        l7=findViewById(R.id.l7);
       text=findViewById(R.id.textView3);
       dates=findViewById(R.id.date);
       lin=findViewById(R.id.linlay) ;
        lottieAnimationView3=findViewById(R.id.lav_actionBar3);

        list.add("0");
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime()).toString();

        SimpleDateFormat sdf = new SimpleDateFormat("E,\n dd \n MMM ");
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -5);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(0));
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(1));
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(2));
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(3));
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(4));
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(5));
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch(list.get(6));
            }
        });

        for(int i = 0; i< 8; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            System.out.println(sdf.format(cal.getTime()));
            list.add(sdff.format(cal.getTime())+"");
            if(i==1){
                d1.setText(sdf.format(cal.getTime())+"");

            }
            if(i==1){
                d2.setText(sdf.format(cal.getTime())+"");

            }
            if(i==2){
                d3.setText(sdf.format(cal.getTime())+"");

            }
            if(i==3){
                d4.setText(sdf.format(cal.getTime())+"");
            }
            if(i==4){
                d5.setText(sdf.format(cal.getTime())+"");

            }
            if(i==5){
                d6.setText(sdf.format(cal.getTime())+"");

            }
            if(i==6){
                d7.setText(sdf.format(cal.getTime())+"");
            }
            if(i==7){
                d7.setText(sdf.format(cal.getTime())+"");
            }


        }

        dates.setText(Date);
        Query checkuser = FirebaseDatabase.getInstance().getReference("DHT11").child("HumidityPred");
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double  tempname= snapshot.child("Data").getValue(Double.class);

                prede.setText(tempname+" °C");
                if(tempname>30){
                   text.setText("IT'S A\n VERY \nHOT DAY");
                    lottieAnimationView1.setVisibility(View.VISIBLE);
                    lottieAnimationView1.playAnimation();
                    lin.setBackgroundColor(0xFFFFA500);

                }else if (tempname<30 && tempname>25){
                    text.setText("IT'S \nCLOUDY \nALL DAY");
                    lottieAnimationView2.setVisibility(View.VISIBLE);
                    lottieAnimationView2.playAnimation();
                    lin.setBackgroundColor(0xFF0000FF);
                }else{
                    text.setText("IT'S\n RAINY \nDAY");
                    lottieAnimationView3.setVisibility(View.VISIBLE);
                    lottieAnimationView3.playAnimation();
                    lin.setBackgroundColor(0xFF008080);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        });


    }
    void fetch(String date){
        Query checkuser = FirebaseDatabase.getInstance().getReference("DHT11").child("Humidity").child(date);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String  tempname= snapshot.getValue(String.class);
                Toast.makeText(MainActivity.this,date,Toast.LENGTH_SHORT).show();
                System.out.println(date);
                dates.setText(date);
                prede.setText(tempname+" °C");
                if(Double.parseDouble(tempname)>30){
                    text.setText("IT'S A\n VERY \nHOT DAY");
                    lottieAnimationView1.setVisibility(View.VISIBLE);
                    lottieAnimationView1.playAnimation();
                    lin.setBackgroundColor(0xFFFFA500);
                }else if (Double.parseDouble(tempname)<30 && Double.parseDouble(tempname)>25){
                    lottieAnimationView1.setVisibility(View.GONE);
                    text.setText("IT'S \nCLOUDY \nALL DAY");
                    lottieAnimationView2.setVisibility(View.VISIBLE);
                    lottieAnimationView2.playAnimation();

                    lin.setBackgroundColor(0xFF0000FF);
                }else{
                    lottieAnimationView1.setVisibility(View.GONE);
                    text.setText("IT'S\n RAINY \nDAY");
                    lottieAnimationView3.setVisibility(View.VISIBLE);
                    lottieAnimationView3.playAnimation();

                    lin.setBackgroundColor(0xFF008080);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        });

    }
}