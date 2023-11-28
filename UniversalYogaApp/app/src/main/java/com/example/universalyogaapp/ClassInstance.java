package com.example.universalyogaapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

// to select the date we have used following method
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

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
                addToDb();
            }
        });


    }

    private void addToDb (){
        // Capture user input from UI
        //String courseId = courseBox.getText().toString();
        String scheduleDate = selectDate.getText().toString().trim();
        String teacherName = scheduleTeacher.getText().toString().trim();
        String additionalComments = comment.getText().toString().trim();

        // Create a ClassSchedule object
        ClassSchedule classSchedule = new ClassSchedule();
        //classSchedule.setCourseId(courseId);
        classSchedule.setDate(scheduleDate);
        classSchedule.setTeacherName(teacherName);
        classSchedule.setAdditionalComments(additionalComments);

        // Call the method to add data to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addClassSchedule(classSchedule, this);

    }
}

