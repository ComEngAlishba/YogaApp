package com.example.universalyogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //All buttons
    Button bAdd, bExit;

    // All required text fields
    EditText etDays, etTime, etCapacity, etDuration, etPrice, etType, etDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // register buttons with their proper IDs.
        bAdd = findViewById(R.id.buttonAdd);
        bExit = findViewById(R.id.buttonExit);

        // register all the EditText fields with their IDs.
        etDays = findViewById(R.id.editTextDays);
        etTime = findViewById(R.id.editTextTime);
        etCapacity = findViewById(R.id.editTextCapacity);
        etDuration = findViewById(R.id.editTextDuration);
        etPrice = findViewById(R.id.editTextPrice);
        etType = findViewById(R.id.editTextType);
        etDescription = findViewById(R.id.editTextDescription);



        // handle the PROCEED button
       bAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String days = etDays.getText().toString();
               String time = etTime.getText().toString();
               String capacity = etCapacity.getText().toString();
               String duration = etDuration.getText().toString();
               String price = etPrice.getText().toString();
               String type = etType.getText().toString();
               String description = etDescription.getText().toString();


               boolean check = validateInfo(days, time, capacity, duration, price, type, description);


               if (check == true) {

                   Intent intent = new Intent(MainActivity.this, CourseConfirmation.class);
                   intent.putExtra("DayOfTheWeek",days);
                   intent.putExtra("Time",time);
                   intent.putExtra("Capacity",capacity);
                   intent.putExtra("Duration",duration);
                   intent.putExtra("Price",price);
                   intent.putExtra("Type",type);
                   intent.putExtra("Description",description);
                   startActivity(intent);

                   Toast.makeText(MainActivity.this, "Hurray Check  The Information", Toast.LENGTH_SHORT).show();
               }
                   else {
                   Toast.makeText(MainActivity.this, "Sorry Check  The Information", Toast.LENGTH_SHORT).show();
               }

           }


           private Boolean validateInfo (String days, String time, String capacity, String duration, String price, String type, String description){
                   if (days.length() == 0) {
                       etDays.requestFocus();
                       etDays.setError("This field is required");
                       return false;
                   } else if (time.length() == 0) {
                       etTime.requestFocus();
                       etTime.setError("This field is required");
                       return false;
                   } else if (capacity.length() == 0) {
                       etCapacity.requestFocus();
                       etCapacity.setError("This field is required");
                       return false;
                   } else if (duration.length() == 0) {
                       etDuration.requestFocus();
                       etDuration.setError("This field is required");
                       return false;
                   } else if (price.length() == 0) {
                       etPrice.requestFocus();
                       etPrice.setError("This field is required");
                       return false;
                   } else if (type.length() == 0) {
                       etType.requestFocus();
                       etType.setError("This field is required");
                       return false;
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






