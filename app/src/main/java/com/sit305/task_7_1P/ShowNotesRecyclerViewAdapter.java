package com.sit305.task_7_1P;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.sit305.task_7_1P.model.Note;


public class ShowNotesRecyclerViewAdapter extends RecyclerView.Adapter<ShowNotesRecyclerViewAdapter.ViewHolder>{

    // The list of notes
    private List<Note> notesList;
    private Context context;

    public ShowNotesRecyclerViewAdapter(List<Note> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    // On create.
    @NonNull
    @Override
    public ShowNotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.notes_display_column, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowNotesRecyclerViewAdapter.ViewHolder holder, int position) {
        // Set the notes data
        holder.notesData_TV.setText(notesList.get(position).getNoteData());
        // Set the note's view ID from the note's db ID
        holder.notes_LL.setId(notesList.get(position).getNote_id());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    // Gets the views to be added to the view holder.
    public class ViewHolder extends RecyclerView.ViewHolder {

        // The view inside this view holder.
        TextView notesData_TV;
        LinearLayout notes_LL;

        // Set the views.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notesData_TV = itemView.findViewById(R.id.notesData_TV);
            notes_LL = itemView.findViewById(R.id.notes_LL);
        }
    }
}
