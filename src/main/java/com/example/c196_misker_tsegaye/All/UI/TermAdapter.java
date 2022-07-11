package com.example.c196_misker_tsegaye.All.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_misker_tsegaye.All.Entities.TermEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {


    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termNameView;
        private final TextView startDateView;
        private final TextView endDateView;

        private TermViewHolder(View itemView){
            super(itemView);
            termNameView = itemView.findViewById(R.id.termName);
            startDateView = itemView.findViewById(R.id.termStartDate);
            endDateView = itemView.findViewById(R.id.termEndDate);
        //make it clickable to go into details
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final TermEntity current = terms.get(position);

                    Intent intent = new Intent(context, TermView.class);
                    intent.putExtra("editTextTextPersonName", current.getTermName());
                    intent.putExtra("editTextDate3", current.getStartDate());
                    intent.putExtra("editTextDate4", current.getEndDate());
                    intent.putExtra("termID" , current.getTermID()+"");
                    context.startActivity(intent);

                }
            });


        }

    }
    private List<TermEntity> terms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.termitem, parent, false);

        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(terms != null){
            TermEntity current = terms.get(position);
            holder.termNameView.setText(current.getTermName());
            holder.endDateView.setText(current.getEndDate());
            holder.startDateView.setText(current.getStartDate());
        }
        else{
            holder.termNameView.setText("none");
            holder.endDateView.setText("none");
            holder.startDateView.setText("none");
        }
    }

    public void setTerms(List<TermEntity> terms)
    {
        this.terms = terms;
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }
}
