package com.example.universalyogaapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Models.DatabaseHelper;

public class Schedule extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addFloatButton, exitFloatButton;
    DatabaseHelper databaseHelper;
    ArrayList<String> scheduleId, courseId, date, teacherName, courseName, comment;
    com.example.universalyogaapp.ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.recyclerView);
        addFloatButton = findViewById(R.id.addFloatButton);
        exitFloatButton = findViewById(R.id.exitFloatButton);

        //back button code
        exitFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, HomePage.class);
                startActivity(intent);
            }
        });

        addFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, ClassInstance.class);
                startActivity(intent);
            }
        });
        databaseHelper = new DatabaseHelper(Schedule.this);
        scheduleId = new ArrayList<>();
        date = new ArrayList<>();
        teacherName = new ArrayList<>();
        courseName = new ArrayList<>();
        comment = new ArrayList<>();

        storeData();

        scheduleAdapter = new com.example.universalyogaapp.ScheduleAdapter(Schedule.this, scheduleId, teacherName, courseName, date, comment);
        recyclerView.setAdapter(scheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Schedule.this));
    }

    void storeData(){
        Cursor cursor = databaseHelper.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Sorry, No Data Present!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                scheduleId.add(cursor.getString(0));
                date.add(cursor.getString(1));
                teacherName.add(cursor.getString(2));
                courseName.add(cursor.getString(3));
                comment.add(cursor.getString(4));

            }
        }

    }

}