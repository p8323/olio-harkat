package com.example.meemigeniskuvatesti3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView meme;
    private Button button;
    FileManager filemanager = null;
    private int GALLERY_REQUEST_CODE = 1;
    private Uri selectedImage;
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
        filemanager.loadImage(maincontext); //loadImage activates onActivityResult
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == GALLERY_REQUEST_CODE) {
                selectedImage = data.getData();
                meme.setImageURI(selectedImage); //sets the image on screen
            }
        }
    }

}
