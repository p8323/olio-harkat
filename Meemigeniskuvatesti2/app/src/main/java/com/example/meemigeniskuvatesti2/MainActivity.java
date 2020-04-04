package com.example.meemigeniskuvatesti2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    private ImageView meme;
    private Button button;
    FileManager filemanager = null;
    Context maincontext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meme = (ImageView) findViewById(R.id.meme);
        button = (Button) findViewById(R.id.button);
        filemanager = FileManager.getInstance();
    }

    public void createMeme(View v) {
        Uri image = filemanager.getImage(maincontext);
        meme.setImageURI(image); //sets image on screen
    }

}

