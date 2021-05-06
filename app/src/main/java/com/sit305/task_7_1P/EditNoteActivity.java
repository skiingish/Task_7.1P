package com.sit305.task_7_1P;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sit305.task_7_1P.data.DatabaseHelper;
import com.sit305.task_7_1P.utility.Const;

public class EditNoteActivity extends AppCompatActivity {

    // The the database helper object.
    DatabaseHelper db;

    // The current notes ID that was passed to be looked up.
    int noteID;

    // The different views.
    EditText editNoteData_ET;
    Button updateNote_BTN;
    Button deleteNote_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Set the views. 
        editNoteData_ET = findViewById(R.id.editNoteData_ET);
        updateNote_BTN = findViewById(R.id.updateNote_BTN);
        deleteNote_BTN = findViewById(R.id.deleteNote_BTN);

        // Get the passed note int if exists.
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                noteID = 0;
            } else {
                noteID = extras.getInt(Const.NOTE_ID);
            }
        }

        // New db object.
        db = new DatabaseHelper(this);

        // Read the current note with the passed note ID.
        String noteText = db.readNoteID(noteID);

        // Set the note edit text to the note text.
        editNoteData_ET.setText(noteText);

        // Update the note text
        updateNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(EditNoteActivity.this);

                int result = db.updateNote(noteID, editNoteData_ET.getText().toString());

                // The the note has been updated close the activity, else show an error.
                if (result > 0)
                {
                    finish();
                }
                else
                {
                    Toast.makeText(EditNoteActivity.this, "Database Error Updating Note", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set the listener for deletion
        deleteNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(EditNoteActivity.this);

                // Delete the note using it's ID.
                long result = db.deleteNote(noteID);

                // The the note has been updated close the activity, else show an error.
                if (result == 1)
                {
                    finish();
                }
                else
                {
                    Toast.makeText(EditNoteActivity.this, "Database Error Deleting Note", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}