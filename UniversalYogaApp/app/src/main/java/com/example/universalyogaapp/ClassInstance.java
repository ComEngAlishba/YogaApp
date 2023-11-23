package com.example.universalyogaapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ClassInstance extends AppCompatActivity {

    private EditText selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_instance);

        selectDate = findViewById(R.id.editTextDate);

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
    }
}