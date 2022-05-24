package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class IarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iario);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date2);
        textViewDate.setText(currentDate);

    }

    public void TodosLosDiarios(View v){
        Intent i = new Intent(this,TodosLosDiarios.class);
        startActivity(i);

    }






}