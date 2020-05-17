package com.example.hello_android;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1) //If wanting to add more entities, use curly braces.
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; //This variable is necessary to turn this variable into a singleton, this way this instance is used everywhere in the app, which can be accessed via the static variable.

    public abstract NoteDao noteDao(); //Used to access the DAO

    public static synchronized NoteDatabase getInstance(Context context) { //Synchronized makes it so that only one thread can access this method, to avoid creating two instances of the database.
        if (instance == null) { //Because we only want to instantiate this database, if we don't already have an instance.
            instance = Room.databaseBuilder(context.getApplicationContext(), //I can't do New NoteDatabase here, so instead i use databaseBuilder
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration() //Tells room how to migrate to new schema, by creating it from scratch to avoid illegal state exception crash
                    .build(); //returns instance of this database
        }
        return instance;
    }
}
