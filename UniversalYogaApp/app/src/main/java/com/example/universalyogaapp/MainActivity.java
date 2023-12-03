package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Models.ClassSchedule;
import Models.Course;
import Models.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    //All buttons
    Button bAdd;
    // All required text fields
    Spinner spDays, spTime, spType;
    EditText  etCapacity, etDuration, etPrice,etDescription;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // register buttons with their proper IDs.
        bAdd = findViewById(R.id.buttonAdd);

        dbHelper = new DatabaseHelper(this);

        // register all the EditText fields with their IDs.
        spDays = findViewById(R.id.spinnerDays);
        spTime = findViewById(R.id.spinnerTime);
        etCapacity = findViewById(R.id.editTextCapacity);
        etDuration = findViewById(R.id.editTextDuration);
        etPrice = findViewById(R.id.editTextPrice);
        spType = findViewById(R.id.spinnerType);

        etDescription = findViewById(R.id.editTextDescription);

        // Get the string array from strings.xml
        String[] days = getResources().getStringArray(R.array.nameOfDays);
        String[] time = getResources().getStringArray(R.array.time);
        String[] type = getResources().getStringArray(R.array.typeOfClass);

        // Create an ArrayAdapter and set it to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDays.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTime.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter2);


        // handle the PROCEED button
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String capacity = etCapacity.getText().toString();
                String duration = etDuration.getText().toString();
                String price = etPrice.getText().toString();
                String description = etDescription.getText().toString();

                boolean check = validateInfo(capacity, duration, price, description);

                if (check) {
                    addCourses();
                    Toast.makeText(MainActivity.this, "Data added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Sorry, Check the Information", Toast.LENGTH_SHORT).show();
                }
            }

            private Boolean validateInfo(String capacity, String duration, String price, String description) {
                if (capacity.length() == 0) {
                    etCapacity.requestFocus();
                    etCapacity.setError("Please provide capacity of the class");
                    return false;
                } else if (duration.length() == 0) {
                    etDuration.requestFocus();
                    etDuration.setError("Please provide duration of the class");
                    return false;
                } else if (price.length() == 0) {
                    etPrice.requestFocus();
                    etPrice.setError("Please provide price of the class");
                    return false;
                } else {
                    return true;
                }
            }
        });
    }

    public void addCourses() {
        // Capture user input from UI
        String selectedDay = spDays.getSelectedItem().toString();
        String selectedTime = spTime.getSelectedItem().toString();
        String capacity = etCapacity.getText().toString().trim();
        String duration = etDuration.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        String selectedType = spType.getSelectedItem().toString();
        String description = etDescription.getText().toString().trim();

        // Create a course object
        Course course = new Course();

        course.setDayOfWeek(selectedDay);
        course.setTimeOfCourse(selectedTime);
        course.setCapacity(Integer.parseInt(capacity));
        course.setDuration(Integer.parseInt(duration));
        course.setPricePerClass(Double.parseDouble(price));
        course.setTypeOfClass(selectedType);
        course.setDescription(description);

        // Call the method to add data to the database
        // dbHelper.addCourse(course, this);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addCourse(course, this);

        //calling the UI after the database
        Intent intent = new Intent(MainActivity.this, Courses.class);
        startActivity(intent);
    }
}











