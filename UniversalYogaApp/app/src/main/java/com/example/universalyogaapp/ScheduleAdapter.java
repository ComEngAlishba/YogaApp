package com.example.universalyogaapp;

import static java.util.Objects.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    Context context;
    ArrayList scheduleId, teacherName, courseName, date, comment;
    int position;
    ScheduleAdapter(Context context, ArrayList scheduleId, ArrayList teacherName, ArrayList courseName, ArrayList date, ArrayList comment) {
        this.context = context;
        this.scheduleId = scheduleId;
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.date = date;
        this.comment = comment;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.schedule_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //this.position = position;
        holder.scheduleIdTxt.setText(String.valueOf(scheduleId.get(position)));
        holder.teacherNameTxt.setText(String.valueOf(teacherName.get(position)));
        holder.courseNameTxt.setText(String.valueOf(courseName.get(position)));
        holder.dateTxt.setText(String.valueOf(date.get(position)));

        //on click listener for schedule details UI
        holder.scheduleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateSchedule.class);
                intent.putExtra("id", String.valueOf(scheduleId.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("teacherName", String.valueOf(teacherName.get(position)));
                intent.putExtra("courseName", String.valueOf(courseName.get(position)));
                intent.putExtra("comment", String.valueOf(comment.get(position)));

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return scheduleId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView scheduleIdTxt, teacherNameTxt, courseNameTxt, dateTxt, commentTxt;
        LinearLayout scheduleDetails;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            scheduleIdTxt = itemView.findViewById(R.id.scheduleId);
            teacherNameTxt = itemView.findViewById(R.id.scheduleTeacher);
            courseNameTxt = itemView.findViewById(R.id.courseNameTxt);
            dateTxt = itemView.findViewById(R.id.scheduleDate);

            scheduleDetails = itemView.findViewById(R.id.scheduleDetails);
            commentTxt = itemView.findViewById(R.id.comment);
        }
    }
}
