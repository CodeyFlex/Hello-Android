package com.example.hello_android;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// The purpose of the ViewModel is to store and process data for the User Interface, and communicate with the model.
// The ViewModel will request data from the repository, so that the activity/fragment can draw it on to the screen. And the ViewModel forwards User interactions from the UI back to the repository.
// Putting UI related data into the ViewModel instead of the UI controller is smart, because the ViewModel will survive configuration changes, whilst the activity will get destroyed and recreated if you flip the screen for example,
// Thereby destroying the state of the member variables of the activity, and causing frustration for the user. (changing language is another example, that will cause a reset.)
// Using ViewModel also avoids potential memory leaks, as the data is not lost in the ViewModel after a config change, thereby making sure that tasks are stopped and restarted when necessary.

public class NoteViewModel extends AndroidViewModel { //AndroidViewModel is a subclass of ViewModel, In this we get passed Application in the constructor.
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) { //Never store context of an activity here, or a view that references an activity, as the ViewModel is designed to outlive the activity. Referencing a destroyed activity here will cause a memory leak.
        super(application);
        repository = new NoteRepository((application)); // It's necessary to pass a context for the repository to instantiate the database instance.
        allNotes = repository.getAllNotes();
    }

    //The activity won't have access to the repository, so this is where i create repo methods for the activity to call:
    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
