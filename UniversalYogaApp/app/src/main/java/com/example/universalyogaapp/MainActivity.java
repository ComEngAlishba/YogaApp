package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {


    //All buttons
    Button bAdd, bExit;

    // All required text fields
    Spinner spDays, spTime, spType;
    EditText  etCapacity, etDuration, etPrice,etDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // register buttons with their proper IDs.
        bAdd = findViewById(R.id.buttonAdd);
        bExit = findViewById(R.id.buttonExit);


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

                String selectedDays = spDays.getSelectedItem().toString();
                String selectedTime = spTime.getSelectedItem().toString();
                String selectedType = spType.getSelectedItem().toString();
                String capacity = etCapacity.getText().toString();
                String duration = etDuration.getText().toString();
                String price = etPrice.getText().toString();
                String description = etDescription.getText().toString();


                boolean check = validateInfo(capacity, duration, price, description);


                if (check == true) {

                    Intent intent = new Intent(MainActivity.this, CourseConfirmation.class);
                    intent.putExtra("Days", "Selected Days: " + selectedDays);
                    intent.putExtra("Time", "Selected Time: " + selectedTime);
                    intent.putExtra("Capacity", "Capacity of the class: " + capacity);
                    intent.putExtra("Duration", "Duration of the class: " + duration);
                    intent.putExtra("Price", "Price of the class: " + price);
                    intent.putExtra("Type", "Selected Type: " + selectedType);
                    intent.putExtra("Description", "Description of the class: " + description);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Hurray Check  The Information", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Sorry Check  The Information", Toast.LENGTH_SHORT).show();
                }

            }


            private Boolean validateInfo(String capacity, String duration, String price, String description) {
//                if (days.length() == 0) {
//                    etDays.requestFocus();
//                    etDays.setError("This field is required");
//                    return false;
//                } else if (time.length() == 0) {
//                    etTime.requestFocus();
//                    etTime.setError("This field is required");
//                    return false; } else
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
//                } else if (type.length() == 0) {
//                    etType.requestFocus();
//                    etType.setError("This field is required");
//                    return false;
                }
//                  else if (description.length() == 0) {
//                  etDescription.requestFocus();
//                  etDescription.setError("This field is required");
//                  return false;
//                 }
                else {
                    return true;
                }
            }

        });
    }
}











