package com.example.vko8_t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BottleDispenser dispenser = null;
    public static TextView text;
    Button add;
    Button buy;
    Button money_back;
    int index=0; //tehtävästä 3.4
    SeekBar seek;
    int progress_changed_value = 0; //alustus

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
    }

    public void addMoney(View v) {
        for (int i=0; i<progress_changed_value; i++) {
            dispenser.addMoney();
        }
        seek.setProgress(0);
    }

    public void buyBottle(View v) {
        //text.setText("");
        dispenser.buyBottleSimple(index); //Tehtävän 3.4 mukainen metodi. Ostetaan aina listan ensimmäinen pullo.
    }

    public void removeBottle(View v) {
        //text.setText("");
        dispenser.returnMoney();
    }
}

