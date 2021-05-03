package com.sit305.task_7_1P.model;

public class Note {

    private int note_id;
    private String noteData;
    private String timestamp;

    public Note() {
    }

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
