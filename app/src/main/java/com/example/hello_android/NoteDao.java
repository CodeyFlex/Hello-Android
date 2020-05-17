package com.example.hello_android;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface NoteDao { //Dao has to be either interfaces or abstract classes, because i don't provide the method body, but instead just create methods and annotate them, and the Note class will automatically generate the code.

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table") //Deletes everything from note_table, this is a custom query.
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes(); //By using LiveData <> i can now observe the data within
}
