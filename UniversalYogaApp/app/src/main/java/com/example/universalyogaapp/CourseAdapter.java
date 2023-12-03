package com.example.universalyogaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Models.Course;
import Models.DatabaseHelper;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    Context context;
    ArrayList courseId, selectedDay, selectedTime, capacity, duration, price, selectedType, description;
    int position;
    CourseAdapter(Context context, ArrayList courseId,ArrayList selectedDay, ArrayList selectedTime, ArrayList capacity, ArrayList duration, ArrayList price, ArrayList selectedType, ArrayList description ) {
        this.context = context;
        this.courseId = courseId;
        this.selectedTime = selectedTime;
        this.selectedDay = selectedDay;
        this.capacity = capacity;
        this.duration = duration;
        this.price = price;
        this.selectedType = selectedType;
        this.description = description;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        this.position = position;
        holder.courseIdTxt.setText(String.valueOf(courseId.get(position)));
        holder.dayTxt.setText(String.valueOf(selectedDay.get(position)));
        holder.yogaTypeTxt.setText(String.valueOf(selectedType.get(position)));
        holder.timeTxt.setText(String.valueOf(selectedTime.get(position)));

        holder.CourseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateCourse.class);

                // Retrieve course details using DatabaseHelper
                int courseIdTxt = Integer.parseInt((String) courseId.get(position));
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                Course course = dbHelper.getCourseByCourseId(courseIdTxt);

                // Set course details to Intent extras
                intent.putExtra("id", course.getCourseId());
                intent.putExtra("time", course.getTimeOfCourse());
                intent.putExtra("day", course.getDayOfWeek());
                intent.putExtra("capacity", String.valueOf(course.getCapacity()));
                intent.putExtra("duration", String.valueOf(course.getDuration()));
                intent.putExtra("price", String.valueOf(course.getPricePerClass()));
                intent.putExtra("yogaType", course.getTypeOfClass());
                intent.putExtra("description", course.getDescription());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseId.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView courseIdTxt, dayTxt, yogaTypeTxt, timeTxt;
        LinearLayout CourseDetails;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIdTxt = itemView.findViewById(R.id.courseIdTxt);
            dayTxt = itemView.findViewById(R.id.dayTxt);
            yogaTypeTxt = itemView.findViewById(R.id.yogaTypeTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);

            CourseDetails = itemView.findViewById(R.id.courseDetails);


        }
    }
}
