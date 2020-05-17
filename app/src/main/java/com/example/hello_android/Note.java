package com.example.hello_android;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table") //Entity is a room annotation, and during compile it will create the code necessary for a SQLite table for this object
public class Note {

    @PrimaryKey(autoGenerate = true) //With @PrimaryKey, SQLite will automatically increment an id for each new row
    private int id;

    private String title;

    private String description;

    private int priority;

    public Note(String title, String description, int priority) { //The constructor to create note objects
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) { //Only a setter for ID, as it is the only valuable not in the constructor, because ID is auto generated.
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
