package com.example.c196_misker_tsegaye.All.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseNameView;
        private final TextView cStartDateView;
        private final TextView cEndDateView;

        private CourseViewHolder(View itemView){
            super(itemView);
            courseNameView = itemView.findViewById(R.id.courseName);
            cStartDateView = itemView.findViewById(R.id.courseStartDate);
            cEndDateView = itemView.findViewById(R.id.courseEndDate);

            //make it clickable to go into details
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final CourseEntity current = courses.get(position);

                    Intent intent = new Intent(context, CourseView.class);
                    intent.putExtra("editCourseName", current.getCourseName());
                    intent.putExtra("courseStartDate", current.getStartDate());
                    intent.putExtra("courseEndDate", current.getEndDate());
                    intent.putExtra("courseID" , current.getCourseID()+"");
                    context.startActivity(intent);

                }
            });

        }
    }

    private List<CourseEntity> courses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.courseitem, parent, false);

        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(courses != null){
            CourseEntity current = courses.get(position);
            holder.courseNameView.setText(current.getCourseName());
            holder.cEndDateView.setText(current.getEndDate());
            holder.cStartDateView.setText(current.getStartDate());
        }
        else{
            holder.courseNameView.setText("none");
            holder.cEndDateView.setText("none");
            holder.cStartDateView.setText("none");
        }
    }

    public void setCourses(List<CourseEntity> courses)
    {
        this.courses = courses;
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
