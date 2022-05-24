package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        }
    public void Diary(View v) {
        Intent i = new Intent(this, IarioActivity.class);
        startActivity(i);

    }

    public void Alters(View v){

        Intent i = new Intent(this, Alters.class);
        startActivity(i);
    }


    public void GraficoFront(View v){
        Intent i = new Intent(this,GraficoFront.class);
        startActivity(i);



    }








}