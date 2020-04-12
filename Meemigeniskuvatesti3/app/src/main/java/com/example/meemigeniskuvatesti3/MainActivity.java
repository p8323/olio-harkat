package com.example.meemigeniskuvatesti3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private FileManager fileManager = null;
    private int GALLERY_REQUEST_CODE = 1;
    private Bitmap image;
    private Context mainContext = this;
    private EditText editText;
    private int width = 0; //width of actual meme
    private int height = 0; //height of actual meme

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.text);
        fileManager = FileManager.getInstance(mainContext);
        textFromFiles();
    }

    public void imageFromGallery(View v) {
        fileManager.loadImage(); //loadImage activates onActivityResult
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                try {
                    Uri imageUri = data.getData();
                    InputStream imagestream = null;
                    imagestream = getContentResolver().openInputStream(imageUri);
                    image = BitmapFactory.decodeStream(imagestream);
                    width = image.getWidth();
                    height = image.getHeight();
                    imageView.setImageBitmap(image); //sets the image on screen
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void imageToGallery(View v) {
        editText.buildDrawingCache();
        Bitmap textMap = Bitmap.createBitmap(editText.getDrawingCache()); //makes bitmap of editText
        Bitmap memeMap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //the meme is constructed here
        Canvas combo = new Canvas(memeMap);
        combo.drawBitmap(image, 0, 0, null);
        combo.drawBitmap(textMap, width/2-editText.getWidth()/2, 0,null);
        fileManager.saveImage(memeMap);
    }

    public void textToFiles(View v) {
        fileManager.writeText(editText);
    }

    public void textFromFiles() {
        fileManager.readText();
    }

}
