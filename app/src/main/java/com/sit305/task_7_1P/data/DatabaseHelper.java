package com.sit305.task_7_1P.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sit305.task_7_1P.model.Note;
import com.sit305.task_7_1P.utility.Const;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "tag";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    }

    // When the database is being created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creation QUERY: Table name, ID, notes data field, and a timestamp field.
        String CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS " + Const.TABLE_NAME + " (" + Const.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Const.NOTE_DATA + " VARCHAR, " + Const.TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    // When it's being updated. (currently will delete all and recreate)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Const.DATABASE_NAME + "." + Const.TABLE_NAME;
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    // Used to insert a new note.
    public long insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NOTE_DATA, note.getNoteData());

        // Insert a new note into the notes table, long is set to the new rowID or -1 if there's a error.
        long newRowId = db.insert(Const.TABLE_NAME, null, values);
        db.close();

        // Return the row ID.
        return newRowId;
    }

    // Read Note By ID
    public String readNoteID(int noteID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Const.TABLE_NAME, new String[]{Const.NOTE_DATA}, Const.NOTE_ID + "=?",
                new String[] {String.valueOf(noteID)}, null, null, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        else
        {
            return "Error Reading Note Data! (Database Error!)";
        }
    }


    // Update Note
    public int updateNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Const.NOTE_DATA, note.getNoteData());

        return db.update(Const.TABLE_NAME, contentValues, Const.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});

    }

    // Delete Note
    public long deleteNote(int noteID) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put(Const.NOTE_ID, note.getNote_id());

        long result = db.delete(Const.TABLE_NAME, Const.NOTE_ID + "=?", new String[]{String.valueOf(noteID)});
        db.close();

        return result;
    }

    // Show All Notes
    public List<Note> readAllNotes() {

        // Create list of notes.
        List<Note> notesList = new ArrayList<Note>();

        // Read the DB
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + Const.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                // Create a new note from the query.
                Note note = new Note();
                note.setNote_id(cursor.getInt(0));
                note.setNoteData(cursor.getString(1));
                // Note set time stamp

                // Add to the list
                notesList.add(note);

            } while (cursor.moveToNext());
        }

        // Return the list of created notes.
        return notesList;
    }
}
