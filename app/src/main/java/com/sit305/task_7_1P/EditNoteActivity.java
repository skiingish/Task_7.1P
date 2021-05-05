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

    DatabaseHelper db;

    // The current notes ID that was passed to be looked up.
    int noteID;

    EditText editNoteData_ET;
    Button deleteNote_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editNoteData_ET = findViewById(R.id.editNoteData_ET);
        deleteNote_BTN = findViewById(R.id.deleteNote_BTN);

        // Get the passed int if exists.
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                noteID = 0;
            } else {
                noteID = extras.getInt(Const.NOTE_ID);
            }
        }

        db = new DatabaseHelper(this);
        String noteText = db.readNoteID(noteID);

        // Set the note edit text to the note text.
        editNoteData_ET.setText(noteText);

        // Set the listener for deletion
        deleteNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(EditNoteActivity.this);

                long result = db.deleteNote(noteID);

                //Toast.makeText(EditNoteActivity.this, "Database Delete Result " + result, Toast.LENGTH_LONG).show();
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