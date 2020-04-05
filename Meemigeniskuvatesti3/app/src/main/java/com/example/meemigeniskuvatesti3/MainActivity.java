package com.example.meemigeniskuvatesti3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView meme;
    private Button button;
    private FileManager fileManager = null;
    private int GALLERY_REQUEST_CODE = 1;
    private Uri imageUri;
    private Bitmap image;
    private Context mainContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meme = (ImageView) findViewById(R.id.meme);
        button = (Button) findViewById(R.id.button);
        fileManager = FileManager.getInstance(mainContext);
    }

    public void createMeme(View v) {
        fileManager.loadImage(); //loadImage activates onActivityResult
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                try {
                    imageUri = data.getData();
                    InputStream imagestream = null;
                    imagestream = getContentResolver().openInputStream(imageUri);
                    image = BitmapFactory.decodeStream(imagestream);
                    meme.setImageBitmap(image); //sets the image on screen
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(View v) {
        fileManager.saveImage(image);
    }

}
