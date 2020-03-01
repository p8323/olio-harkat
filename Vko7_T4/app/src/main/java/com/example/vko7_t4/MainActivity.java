package com.example.vko7_t4;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText input;
    String input_text;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.editText);
        handler.post(labelfiller);
    }

    /*public void button(View v) {
        input_text = input.getText().toString();
        text.setText(input_text);
    }*/

    private Runnable labelfiller = new Runnable() {
        @Override
        public void run() {
            input_text = input.getText().toString();
            text.setText(input_text);
            text.postDelayed(this, 500);
        }
    };
}



