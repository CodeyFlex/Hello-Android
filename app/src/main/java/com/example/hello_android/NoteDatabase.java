package com.example.hello_android;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1) //If wanting to add more entities, use curly braces.
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; //This variable is necessary to turn this variable into a singleton, this way this instance is used everywhere in the app, which can be accessed via the static variable.

    public abstract NoteDao noteDao(); //Used to access the DAO

    public static synchronized NoteDatabase getInstance(Context context) { //Synchronized makes it so that only one thread can access this method, to avoid creating two instances of the database.
        if (instance == null) { //Because we only want to instantiate this database, if we don't already have an instance.
            instance = Room.databaseBuilder(context.getApplicationContext(), //I can't do New NoteDatabase here, so instead i use databaseBuilder
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration() //Tells room how to migrate to new schema, by creating it from scratch to avoid illegal state exception crash
                    .addCallback(roomCallback)
                    .build(); //returns instance of this database
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) { //onCreate because i only want to create the example notes once, the first time the database is written.
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> { //Populates the database
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
