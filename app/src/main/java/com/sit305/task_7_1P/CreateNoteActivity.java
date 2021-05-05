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

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        EditText noteData_ET = findViewById(R.id.noteData_ET);
        Button saveNote_BTN = findViewById(R.id.saveNote_BTN);

        db = new DatabaseHelper(this);

        saveNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noteData = noteData_ET.getText().toString();

                long rowIDResult = db.insertNote(new Note(noteData));

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