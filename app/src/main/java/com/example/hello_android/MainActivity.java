package com.example.hello_android;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method will change the text of my main text view
    public void changeText(View view) {
        TextView main_text_view = (TextView) findViewById(R.id.main_text_view);
        EditText plain_text_input = (EditText) findViewById(R.id.plain_text_input);
        main_text_view.setText("" + plain_text_input.getText());
    }
}
