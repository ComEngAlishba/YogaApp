package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CourseConfirmation extends AppCompatActivity {

    Button bEdit, bSubmit;

    // All required text fields

    TextView vDays,vTime, vCapacity, vDuration,vPrice,vType, vDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_confirmation);

        vDays = findViewById(R.id.viewDays);
        vTime = findViewById(R.id.viewTime);
        vCapacity = findViewById(R.id.viewCapacity);
        vDuration = findViewById(R.id.viewDuration);
        vPrice = findViewById(R.id.viewPrice);
        vType = findViewById(R.id.viewType);
        vDescription = findViewById(R.id.viewDescription);

        bEdit = findViewById(R.id.buttonEdit);
        bSubmit = findViewById(R.id.buttonSubmit);

        String txtDays = getIntent().getStringExtra("Days");
        String txtTime = getIntent().getStringExtra("Time");
        String txtCapacity = getIntent().getStringExtra("Capacity");
        String txtDuration = getIntent().getStringExtra("Duration");
        String txtPrice = getIntent().getStringExtra("Price");
        String txtType = getIntent().getStringExtra("Type");
        String txtDescription = getIntent().getStringExtra("Description");

        vDays.setText(txtDays);
        vTime.setText(txtTime);
        vCapacity.setText(txtCapacity);
        vDuration.setText(txtDuration);
        vPrice.setText(txtPrice);
        vType.setText(txtType);
        vDescription.setText(txtDescription);

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               Intent intent = new Intent(CourseConfirmation.this, com.example.universalyogaapp.MainActivity.class);
               startActivity(intent);
            }
        });
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(CourseConfirmation.this, "You have sucessfully sumbmitted the Information", Toast.LENGTH_SHORT).show();
            }
        });
    }


}