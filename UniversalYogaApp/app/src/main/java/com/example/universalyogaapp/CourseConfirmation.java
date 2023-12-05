package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Activity for displaying and confirming course information
public class CourseConfirmation extends AppCompatActivity {

    // Buttons for editing and submitting information
    Button bEdit, bSubmit;

    // All required text fields
    TextView vDays,vTime, vCapacity, vDuration,vPrice,vType, vDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_confirmation);

        // Initialize text views and buttons from the layout
        vDays = findViewById(R.id.viewDays);
        vTime = findViewById(R.id.viewTime);
        vCapacity = findViewById(R.id.viewCapacity);
        vDuration = findViewById(R.id.viewDuration);
        vPrice = findViewById(R.id.viewPrice);
        vType = findViewById(R.id.viewType);
        vDescription = findViewById(R.id.viewDescription);

        bEdit = findViewById(R.id.buttonEdit);
        bSubmit = findViewById(R.id.buttonSubmit);

        // Retrieve course details from the intent
        String txtDays = getIntent().getStringExtra("Days");
        String txtTime = getIntent().getStringExtra("Time");
        String txtCapacity = getIntent().getStringExtra("Capacity");
        String txtDuration = getIntent().getStringExtra("Duration");
        String txtPrice = getIntent().getStringExtra("Price");
        String txtType = getIntent().getStringExtra("Type");
        String txtDescription = getIntent().getStringExtra("Description");

        // Set retrieved details to corresponding text views
        vDays.setText(txtDays);
        vTime.setText(txtTime);
        vCapacity.setText(txtCapacity);
        vDuration.setText(txtDuration);
        vPrice.setText(txtPrice);
        vType.setText(txtType);
        vDescription.setText(txtDescription);

        // Set up onClickListener for the "Edit" button to navigate to MainActivity
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               Intent intent = new Intent(CourseConfirmation.this, com.example.universalyogaapp.MainActivity.class);
               startActivity(intent);
            }
        });

        // Set up onClickListener for the "Submit" button to display a toast message
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(CourseConfirmation.this, "You have sucessfully sumbmitted the Information", Toast.LENGTH_SHORT).show();
            }
        });
    }


}