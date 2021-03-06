package com.example.vko8_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottleDispenser dispenser = null;
    public static TextView text;
    Button add;
    Button buy;
    Button money_back;
    int index=0; //tehtävästä 3.4
    SeekBar seek;
    int progress_changed_value = 0; //alustus
    Spinner spinner;
    String dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        add = (Button) findViewById(R.id.add);
        buy = (Button) findViewById(R.id.buy);
        money_back = (Button) findViewById(R.id.money_back);
        dispenser = BottleDispenser.getInstance();
        seek = (SeekBar) findViewById(R.id.seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_changed_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Adding :"+progress_changed_value, Toast.LENGTH_SHORT).show();
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerFunctions();
    }

    public void addMoney(View v) {
        for (int i=0; i<progress_changed_value; i++) {
            dispenser.addMoney();
        }
        seek.setProgress(0);
    }

    public void buyBottle(View v) {
        //text.setText("");
        try {
            dropdown = String.valueOf(spinner.getSelectedItem());
            dispenser.buyBottle(dropdown); //Tehtävän 3.4 mukainen metodi. Ostetaan aina listan ensimmäinen pullo.
        } catch (Exception e) {
            text.setText("Bottle not available.");
        }
    }

    public void removeBottle(View v) {
        //text.setText("");
        dispenser.returnMoney();
    }

    public void spinnerFunctions() {
        ArrayList<String> selections = new ArrayList<String>();
        for (int i=0; i<dispenser.bottles; i++) {
            selections.add(dispenser.bottle_array.get(i).getName()+" "+dispenser.bottle_array.get(i).getSize()+" "+dispenser.bottle_array.get(i).getPrize()+"€");
        }
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


