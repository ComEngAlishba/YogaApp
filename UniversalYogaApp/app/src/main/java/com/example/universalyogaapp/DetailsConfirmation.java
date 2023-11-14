package com.example.universalyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class DetailsConfirmation extends AppCompatActivity {
    //All buttons
    Button bEdit, bSubmit;

    // All required text fields

    TextView vDays,Price, Time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_confirmation);

        vDays = findViewById(R.id.viewDays);
        Time = findViewById(R.id.viewTime);
        Price = findViewById(R.id.viewPrice);

        String txtDays;
        String txtTime;


         txtDays = getIntent().getStringExtra("DayOfTheWeek");
         txtTime = getIntent().getStringExtra("Time");

        vDays.setText("Days of the Week" + txtDays);
        Time.setText("Days of the Week" + txtTime);


    }
}








