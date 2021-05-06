package com.sit305.task_7_1P;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sit305.task_7_1P.data.DatabaseHelper;
import com.sit305.task_7_1P.model.Note;
import com.sit305.task_7_1P.utility.Const;

import java.util.ArrayList;
import java.util.List;

public class ShowAllNotesActivity extends AppCompatActivity {

    // The DB object.
    DatabaseHelper db;

    // The Recycler
    RecyclerView notes_RV;
    ShowNotesRecyclerViewAdapter showNotesRecyclerViewAdapter;

    // The notes list
    List<Note> notesList = new ArrayList<>();

    String[] tempNotesArray =  {"Hello I am Note", "Hello I am also a Note"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notes);

        // Get the recycler view;
        notes_RV = findViewById(R.id.notes_RV);

        // Read the db and display the notes.
        buildNotesRecyclerView();
    }

    // When coming back to this activity reload the notes recycler view from the DB.
    @Override
    protected void onResume() {
        super.onResume();

        // Read the db and display the notes.
        buildNotesRecyclerView();
    }

    public void onClickShowSelection(View view)
    {
        // Get the ID of the note that was clicked.
        int viewID = view.getId();

        // Open the edit note Activity.
        Intent editNoteIntent = new Intent(ShowAllNotesActivity.this, EditNoteActivity.class);

        // Pass the note ID.
        editNoteIntent.putExtra(Const.NOTE_ID,viewID);

        // Start the activity.
        startActivity(editNoteIntent);

    }

    private void buildNotesRecyclerView()
    {
        // Get the list of notes from the database;
        db = new DatabaseHelper(this);
        notesList = db.readAllNotes();

        // Build the notes list.
        showNotesRecyclerViewAdapter = new ShowNotesRecyclerViewAdapter(notesList, this);
        notes_RV.setAdapter(showNotesRecyclerViewAdapter);
        notes_RV.setLayoutManager(new LinearLayoutManager(this));
    }
}