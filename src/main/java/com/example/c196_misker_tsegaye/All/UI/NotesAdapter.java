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
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    class NotesViewHolder extends RecyclerView.ViewHolder{
        private final TextView noteNameView;

        private NotesViewHolder(View itemView){
            super(itemView);

            noteNameView = itemView.findViewById(R.id.noteName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final NoteEntity current = notes.get(position);

                    Intent intent = new Intent(context, NoteView.class);
                    intent.putExtra("noteName", current.getNoteName());
                    intent.putExtra("noteID", current.getNoteID()+"");
                    context.startActivity(intent);

                }
            });

        }
    }

    private List<NoteEntity> notes;
    private final Context context;
    private final LayoutInflater mInflater;

    public NotesAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.notesitem, parent, false);

        return new NotesAdapter.NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        if(notes != null){
            NoteEntity current = notes.get(position);
            holder.noteNameView.setText(current.getNoteName());

        }
        else{
            holder.noteNameView.setText("none");

        }
    }

    public void setAssessments(List<NoteEntity> notes)
    {
        this.notes = notes;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
