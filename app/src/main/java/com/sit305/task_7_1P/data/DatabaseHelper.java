package com.sit305.task_7_1P.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sit305.task_7_1P.model.Note;
import com.sit305.task_7_1P.utility.Const;

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
}
