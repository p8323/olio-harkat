package com.example.vko8_t2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BottleDispenser dispenser = null;
    public static TextView text;
    Button add;
    Button buy;
    Button money_back;
    int index=0; //tehtävästä 3.4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        add = (Button) findViewById(R.id.add);
        buy = (Button) findViewById(R.id.buy);
        money_back = (Button) findViewById(R.id.money_back);
        dispenser = BottleDispenser.getInstance();
    }

    public void addMoney(View v) {
        dispenser.addMoney();
    }

    public void buyBottle(View v) {
        text.setText("");
        dispenser.buyBottleSimple(index); //Tehtävän 3.4 mukainen metodi. Ostetaan aina listan ensimmäinen pullo.
    }

    public void removeBottle(View v) {
        text.setText("");
        dispenser.returnMoney();
    }
}
