package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Schedule extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addFloatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.recyclerView);
        addFloatButton = findViewById(R.id.addFloatButton);

        addFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, ClassInstance.class);
                startActivity(intent);
            }
        });
    }
}