package com.example.c196_misker_tsegaye.All.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.R;

import org.w3c.dom.Text;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends  RecyclerView.ViewHolder{
        private final TextView assessmentNameView;

        private AssessmentViewHolder(View itemView){
            super(itemView);

            assessmentNameView = itemView.findViewById(R.id.assessmentName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final AssessmentEntity current = assessments.get(position);

                    Intent intent = new Intent(context, AssessmentView.class);
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentID", current.getAssessmentID()+"");
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<AssessmentEntity> assessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessmentitem, parent, false);

        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(assessments != null){
            AssessmentEntity current = assessments.get(position);
            holder.assessmentNameView.setText(current.getAssessmentName());

        }
        else{
            holder.assessmentNameView.setText("none");

        }
    }

    public void setAssessments(List<AssessmentEntity> assessments)
    {
        this.assessments = assessments;
    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }
}
