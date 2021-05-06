package com.sit305.task_7_1P.model;

// Notes class
public class Note {

    // Note ID.
    private int note_id;

    // The user entered note data.
    private String noteData;

    // The timestamp of when the note was created.
    private String timestamp;

    // Ctor
    public Note() {
    }

    // Setter and Getter methods for the note fields.
    public Note(String noteData) {
        this.noteData = noteData;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
