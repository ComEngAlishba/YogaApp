package com.example.universalyogaapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class ViewCourse extends AppCompatActivity {
    //All buttons
    Button bEdit, bSubmit;

    // All required text fields

    TextView vDays,Price, Time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_course);



    }
}








