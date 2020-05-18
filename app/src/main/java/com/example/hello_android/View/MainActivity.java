package com.example.hello_android.View;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.hello_android.Note;
import com.example.hello_android.NoteViewModel;
import com.example.hello_android.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class); //Passing "this" activity/fragment lets the ViewModel know which lifecycle it has to be scoped to, so this ViewModel will be destroyed when this activity is finished.
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() { //The first value is the lifecycle owner, in this case it is "this", the second value is the observer.
            @Override
            public void onChanged(List<Note> notes) { //This method will get triggered every time the data in the LiveData object changes.
                // TODO: 19/05/2020 RecyclerView 
            }
        });
    }

    //This method will change the text of my main text view
/*    public void changeText(View view) {
        TextView main_text_view = (TextView) findViewById(R.id.main_text_view);
        EditText plain_text_input = (EditText) findViewById(R.id.plain_text_input);
        main_text_view.setText("" + plain_text_input.getText());
   }*/
}
