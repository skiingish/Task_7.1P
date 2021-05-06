package com.sit305.task_7_1P;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the button views.
        Button createNote_BTN = findViewById(R.id.createNote_BTN);
        Button showNote_BTN = findViewById(R.id.showNotes_BTN);

        // Set up the create note Intent.
        createNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNotesIntent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(createNotesIntent);
            }
        });

        // Set up the show all notes Intent.
        showNote_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNotesIntent = new Intent(MainActivity.this, ShowAllNotesActivity.class);
                startActivity(showNotesIntent);
            }
        });
    }
}