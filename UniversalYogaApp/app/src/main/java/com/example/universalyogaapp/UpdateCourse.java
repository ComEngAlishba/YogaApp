package com.example.universalyogaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import Models.Course;
import Models.DatabaseHelper;

public class UpdateCourse extends AppCompatActivity {

    Button buttonUpdate, buttonDelete;
    // All required text fields
    Spinner spinnerDays2, spinnerTime2, spinnerType2;
    EditText etCapacity2, etDuration2, etPrice2,etDescription2;

    String id, day, selectedTime, capacity, duration, price, yogaType, description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        spinnerDays2 = findViewById(R.id.spinnerDays2);
        spinnerTime2 = findViewById(R.id.spinnerTime2);
        spinnerType2 = findViewById(R.id.spinnerType2);
        etCapacity2 = findViewById(R.id.editTextCapacity2);
        etDuration2 = findViewById(R.id.editTextDuration2);
        etPrice2 = findViewById(R.id.editTextPrice2);
        etDescription2 = findViewById(R.id.editTextDescription2);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBox(); // called this function for dialogBox to appear before deletion of data
            }
        });

        //code for updating course
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Retrieve updated course details from UI elements
                String day = spinnerDays2.getSelectedItem().toString();
                String time = spinnerTime2.getSelectedItem().toString();
                String capacity = etCapacity2.getText().toString();
                String duration = etDuration2.getText().toString();
                String price = etPrice2.getText().toString();
                String yogaType = spinnerType2.getSelectedItem().toString();
                String description = etDescription2.getText().toString();

                // Update the course details using DatabaseHelper
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateCourse.this);
                boolean isUpdated = dbHelper.updateCourse(id, day, time, capacity, duration, price, yogaType, description);

                // Display a toast message based on the update result
                if (isUpdated) {
                    Toast.makeText(UpdateCourse.this, "Course updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateCourse.this, "Failed to update course", Toast.LENGTH_SHORT).show();
                }

                // Navigate back to the Courses activity after updating the course
                Intent intent = new Intent(UpdateCourse.this, Courses.class);
                startActivity(intent);
            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("day") && getIntent().hasExtra("time") && getIntent().hasExtra("capacity") && getIntent().hasExtra("duration") && getIntent().hasExtra("price") && getIntent().hasExtra("yogaType") && getIntent().hasExtra("description") ){
            // Getting Data from intent
            id = getIntent().getStringExtra("id");
            day = getIntent().getStringExtra("day");
            selectedTime = getIntent().getStringExtra("time");
            capacity = getIntent().getStringExtra("capacity");
            duration = getIntent().getStringExtra("duration");
            price = getIntent().getStringExtra("price");
            yogaType = getIntent().getStringExtra("yogaType");
            description = getIntent().getStringExtra("description");

            // Now we can use these variables to set data in our UI elements
            String[] nameOfDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String[] time = {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};
            String[] typeOfClass = {"Flow Yoga", "Aerial Yoga", "Family Yoga"};

            // Setting data to courseDetails
            // Set selection for Spinners based on the received data
            spinnerDays2.setSelection(Arrays.asList(nameOfDays).indexOf(day));
            spinnerTime2.setSelection(Arrays.asList(time).indexOf(selectedTime));
            etCapacity2.setText(capacity);
            etDuration2.setText(duration);
            etPrice2.setText(price);
            spinnerType2.setSelection(Arrays.asList(typeOfClass).indexOf(yogaType));
            etDescription2.setText(description);

        } else {
            Toast.makeText(this, "Sorry! No Data present!", Toast.LENGTH_SHORT).show();
        }
    }

        public void dialogBox(){
            // Create an alert dialog builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set the title of the dialog box
            builder.setTitle("Delete " + yogaType + "?");
            // Set the message of the dialog box
            builder.setMessage("Are you sure you want to delete ?");

            // Set the positive button action
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Delete the class schedule
                    DatabaseHelper dbHelper = new DatabaseHelper(UpdateCourse.this);
                    dbHelper.deleteCourse(Integer.parseInt(id), UpdateCourse.this);

                    // Navigate back to the Schedule activity
                    Intent intent = new Intent(UpdateCourse.this, Courses.class);
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