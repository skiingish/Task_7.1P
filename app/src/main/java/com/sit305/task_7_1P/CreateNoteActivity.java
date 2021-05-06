package com.sit305.task_7_1P;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sit305.task_7_1P.data.DatabaseHelper;
import com.sit305.task_7_1P.model.Note;

public class CreateNoteActivity extends AppCompatActivity {

    // DatabaseHelper object
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        // Set the views
        EditText noteData_ET = findViewById(R.id.noteData_ET);
        Button saveNote_BTN = findViewById(R.id.saveNote_BTN);

        // New DatabaseHelper object.
        db = new DatabaseHelper(this);

        // When the save not button is clicked.
        saveNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the text from edit text field to store as note data.
                String noteData = noteData_ET.getText().toString();

                // Create a new note row in the db with the user stored data.
                long rowIDResult = db.insertNote(new Note(noteData));

                // If successful close the activity else show an error.
                if (rowIDResult > 0)
                {
                    finish();
                }
                else
                {
                    Toast.makeText(CreateNoteActivity.this, "Error creating note: " + rowIDResult, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}