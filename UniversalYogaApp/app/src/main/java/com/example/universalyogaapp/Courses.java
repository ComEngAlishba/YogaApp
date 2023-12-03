package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Models.DatabaseHelper;

public class Courses extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addFloatButton, exitFloatButton;
    DatabaseHelper databaseHelper;
    com.example.universalyogaapp.CourseAdapter courseAdapter;
    ArrayList<String> courseId, selectedDay, selectedTime, selectedType, capacity, duration, price, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        recyclerView = findViewById(R.id.recyclerView);
        addFloatButton = findViewById(R.id.addFloatButton);
        exitFloatButton = findViewById(R.id.exitFloatButton);

        //back button code
        exitFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, HomePage.class);
                startActivity(intent);
            }
        });

        addFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, com.example.universalyogaapp.MainActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(Courses.this);
        courseId = new ArrayList<>();
        selectedDay = new ArrayList<>();
        selectedTime = new ArrayList<>();
        capacity = new ArrayList<>();
        duration = new ArrayList<>();
        price = new ArrayList<>();
        selectedType = new ArrayList<>();
        description = new ArrayList<>();

        storeData();

        courseAdapter = new com.example.universalyogaapp.CourseAdapter(Courses.this, courseId, selectedDay, selectedTime, capacity, duration, price, selectedType, description );
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Courses.this));
    }

    void storeData(){
        Cursor cursor = databaseHelper.readCourses();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Sorry, No Data Present!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                courseId.add(cursor.getString(0));
                selectedDay.add(cursor.getString(1));
                selectedTime.add(cursor.getString(2));
                capacity.add(cursor.getString(3));
                duration.add(cursor.getString(4));
                price.add(cursor.getString(5));
                selectedType.add(cursor.getString(6));
                description.add(cursor.getString(7));

            }
        }

    }
}