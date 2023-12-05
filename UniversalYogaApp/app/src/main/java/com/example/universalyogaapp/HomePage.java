package com.example.universalyogaapp;

import static com.example.universalyogaapp.R.id.courseAdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    /**
     * Declare the CardView elements for adding and viewing courses and schedules.
     */
    public CardView addCourse, addSchedule, viewCourse, viewSchedule;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        addCourse = findViewById(courseAdd);
        addSchedule = findViewById(R.id.scheduleAdd);
        viewCourse = findViewById(R.id.courseView);
        viewSchedule = findViewById(R.id.scheduleView);

        //for addCourse
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the next activity
                Intent intent = new Intent(HomePage.this, com.example.universalyogaapp.MainActivity.class);
                startActivity(intent);
            }
        });

        //for addSchedule
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ClassInstance.class);
                startActivity(intent);
            }
        });
        //for viewing course
        viewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Courses.class);
                startActivity(intent);
            }
        });
        //for viewing schedule
        viewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Schedule.class);
                startActivity(intent);
            }
        });


    }


}