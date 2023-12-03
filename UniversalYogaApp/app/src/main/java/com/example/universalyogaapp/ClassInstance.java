package com.example.universalyogaapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import Models.ClassSchedule;
import Models.DatabaseHelper;

public class ClassInstance extends AppCompatActivity {

    Spinner courseBox;
    EditText selectDate, scheduleTeacher, comment;
    Button addSchedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_instance);


        courseBox = findViewById(R.id.courseBox);
        selectDate = findViewById(R.id.scheduleDate);
        scheduleTeacher = findViewById(R.id.scheduleTeacher);
        comment = findViewById(R.id.comment);
        addSchedule = findViewById(R.id.addSchedule);

        // Get the string array from strings.xml
        String[] type = getResources().getStringArray(R.array.typeOfClass);

        // Create an ArrayAdapter and set it to the spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseBox.setAdapter(adapter2);

// to select the date we have used following method
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Retrieve the selected course type from the Intent
        String selectedType = getIntent().getStringExtra("selectedType");
        // Set the selected course type in the courseBox Spinner
        if (selectedType != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{selectedType});
            courseBox.setAdapter(adapter);
            courseBox.setSelection(0);
        }

        selectDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(ClassInstance.this, (view, year1, month1, dayOfMonth) -> {
                month1 = month1 + 1;
                String date = dayOfMonth + "/" + month1 + "/" + year1;
                selectDate.setText(date);

            }, year, month, day);
            dialog.show();
        });

        //setting up on click listener for add button
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scheduleDate = selectDate.getText().toString().trim();
                String teacherName = scheduleTeacher.getText().toString().trim();
                String courseName = courseBox.getSelectedItem().toString();
                String additionalComments = comment.getText().toString().trim();

                boolean check = validateInfo(scheduleDate, teacherName, additionalComments);

                if (check) {
                    addToDb();
                    Toast.makeText(ClassInstance.this, "Data added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClassInstance.this, "Sorry, Check the Information", Toast.LENGTH_SHORT).show();
                }
            }
            private Boolean validateInfo(String scheduleDate, String teacherName, String additionalComments) {
                if (scheduleDate.length() == 0) {
                    selectDate.requestFocus();
                    selectDate.setError("Please provide date of the class");
                    return false;
                } else if (teacherName.length() == 0) {
                    scheduleTeacher.requestFocus();
                    scheduleTeacher.setError("Please provide teacher of the class");
                    return false;
                } else {
                    return true;
                }
            }
        });
    }
    private void addToDb(){
        // Capture user input from UI

        String scheduleDate = selectDate.getText().toString().trim();
        String teacherName = scheduleTeacher.getText().toString().trim();
        String courseName = courseBox.getSelectedItem().toString();
        String additionalComments = comment.getText().toString().trim();

        // Create a ClassSchedule object
        ClassSchedule classSchedule = new ClassSchedule();
        //classSchedule.setCourseId(courseId);
        classSchedule.setDate(scheduleDate);
        classSchedule.setTeacherName(teacherName);
        classSchedule.setCourseName(courseName);
        classSchedule.setAdditionalComments(additionalComments);

        // Call the method to add data to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addClassSchedule(classSchedule, this);

        //calling the UI after the database
        Intent intent = new Intent(ClassInstance.this, Schedule.class);
        startActivity(intent);

    }



}

