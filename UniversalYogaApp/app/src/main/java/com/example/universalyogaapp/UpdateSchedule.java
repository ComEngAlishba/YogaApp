package com.example.universalyogaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import Models.ClassSchedule;
import Models.DatabaseHelper;


public class UpdateSchedule extends AppCompatActivity {

    Spinner courseName;
    EditText date, teacherName, comment;
    Button updateButton, DeleteBtn;
    String id, update_date, update_teacherName, update_courseName, update_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

        date = findViewById(R.id.update_scheduleDate);
        teacherName = findViewById(R.id.update_scheduleTeacher);
        courseName = findViewById(R.id.update_courseName);
        comment = findViewById(R.id.update_comment);
        updateButton = findViewById(R.id.updateButton);
        DeleteBtn = findViewById(R.id.DeleteBtn);

        //deleting data
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBox(); //called this function
            }

        });


        //updating schedule
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated information from the input fields
                String newDate = date.getText().toString();
                String newTeacherName = teacherName.getText().toString();
                String newCourseName = courseName.getSelectedItem().toString();
                String newComment = comment.getText().toString();

                // Create a DatabaseHelper instance to access the database
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateSchedule.this);
                // Call the updateClassSchedule method to update the schedule in the database
                boolean isUpdated = dbHelper.updateClassSchedule(id, newDate, newTeacherName, newCourseName, newComment);

                // Check if the update was successful
                if (isUpdated) {
                    // Display a toast message indicating successful update
                    Toast.makeText(UpdateSchedule.this, "Schedule updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Display a toast message indicating failed update
                    Toast.makeText(UpdateSchedule.this, "Failed to update schedule", Toast.LENGTH_SHORT).show();
                }

                // Create an intent to navigate back to the Schedule activity
                Intent intent = new Intent(UpdateSchedule.this, Schedule.class);
                startActivity(intent);

            }
        });
        getAndSetIntentData(); // called this function
    }

    void getAndSetIntentData(){

        // Check if the intent contains the required data
        if (getIntent().hasExtra("id") && getIntent().hasExtra("date") && getIntent().hasExtra("teacherName")&& getIntent().hasExtra("courseName") && getIntent().hasExtra("comment")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            update_date = getIntent().getStringExtra("date");
            update_teacherName = getIntent().getStringExtra("teacherName");
            update_courseName = getIntent().getStringExtra("courseName");
            update_comment = getIntent().getStringExtra("comment");

            // Now we can use these variables to set data in our UI elements
            String[] typeOfClass = {"Flow Yoga", "Aerial Yoga", "Family Yoga"};
            courseName.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeOfClass));
            courseName.setSelection(Arrays.asList(typeOfClass).indexOf(update_courseName)); // Set selection for Spinners based on the received data

            //setting intent data
            date.setText(update_date);
            teacherName.setText(update_teacherName);
            comment.setText(update_comment);

        }else {
            // Display a toast message if the intent does not contain the required data
            Toast.makeText(this, "No data present", Toast.LENGTH_SHORT).show();

        }
    }
    public void dialogBox(){
        // Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the title of the dialog box
        builder.setTitle("Delete " + update_date + "?");
        // Set the message of the dialog box
        builder.setMessage("Are you sure you want to delete schedule with " + update_teacherName + "?");
        // Set the positive button action
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Delete the class schedule
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateSchedule.this);
                dbHelper.deleteClassSchedule(Integer.parseInt(id), UpdateSchedule.this);

                // Navigate back to the Schedule activity
                Intent intent = new Intent(UpdateSchedule.this, Schedule.class);
                startActivity(intent);

            }
        });
        // Set the negative button action
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing if the user clicks the "No" button
            }
        });
        // Show the dialog box
        builder.create().show();

    }
}