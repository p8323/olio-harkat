package com.example.meemigeniskuvatesti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView meme;
    Spinner spinner;
    String selection; //current text of spinner
    ArrayList<String> images = new ArrayList<String>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meme = (ImageView) findViewById(R.id.meme);
        Button button = (Button) findViewById(R.id.button);
        addImage();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerFunctions();
    }

    public void createMeme(View v) {
        selection = String.valueOf(spinner.getSelectedItem());
        context = meme.getContext();
        int imageid = context.getResources().getIdentifier(selection, "drawable", context.getPackageName());
        meme.setImageResource(imageid);

    }

    public void spinnerFunctions() {
        ArrayList<String> selections = new ArrayList<String>();
        for (int i=0; i<images.size(); i++) {
            selections.add(images.get(i));
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

    public void addImage() { //new images must be added to arraylist 'images' here MANUALLY
        images.add("winnietheflu_blank");
        images.add("cj");
        images.add("meandtheboys");
    }

}
