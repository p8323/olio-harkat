package com.example.vko9_t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Context context = null;
    TextView advice;
    TextView movies;
    String date;
    String time;
    String name;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        advice = (TextView) findViewById(R.id.advice);
        movies = (TextView) findViewById(R.id.movies);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerFunctions();
        context = MainActivity.this;
    }

    public void spinnerFunctions() {
        ArrayList<String> selections = new ArrayList<String>();
        /*for (int i=0; i<dispenser.bottles; i++) {
            selections.add(dispenser.bottle_array.get(i).getName()+" "+dispenser.bottle_array.get(i).getSize()+" "+dispenser.bottle_array.get(i).getPrize()+"€");
        }*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
