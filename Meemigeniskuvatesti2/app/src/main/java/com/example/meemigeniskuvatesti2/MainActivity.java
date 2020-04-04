package com.example.meemigeniskuvatesti2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MainActivity extends AppCompatActivity {

    private ImageView meme;
    private Button button;
    FileManager filemanager = null;
    private int GALLERY_REQUEST_CODE = 1;
    Uri selectedImage;
    Context maincontext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meme = (ImageView) findViewById(R.id.meme);
        button = (Button) findViewById(R.id.button);
        filemanager = FileManager.getInstance();
    }

    public void createMeme(View v) { //TODO tee filemanagerille activity ja siirrä galleria toiminto getImagelle.
        Uri image = filemanager.getImage(maincontext);
        meme.setImageURI(image);
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestGalleryPermission();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Intent photopickerintent = new Intent(Intent.ACTION_PICK);
            photopickerintent.setType("image/*");
            //String[] mimeTypes = {"image/jpeg", "image/png"};
            //photopickerintent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(photopickerintent, GALLERY_REQUEST_CODE);
        }*/
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == GALLERY_REQUEST_CODE) {
                selectedImage = data.getData(); //FileNotFoundException?
                meme.setImageURI(selectedImage);
            }
        }
    }

    private void requestGalleryPermission() { //TODO lisää shouldShowRequestPermissionRationale jos tulee ongelmia
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, GALLERY_REQUEST_CODE);
    }

}

