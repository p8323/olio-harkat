package com.example.meemigeniskuvatesti2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FileManager extends AppCompatActivity {

    private int GALLERY_REQUEST_CODE = 1;
    Uri selectedImage = null;
    private static FileManager instance = null;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    public Uri getImage(Context context) {
        if (ContextCompat.checkSelfPermission((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestGalleryPermission();
        }
        if (ContextCompat.checkSelfPermission((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Intent photopickerintent = new Intent(Intent.ACTION_PICK);
            photopickerintent.setType("image/*");
            //String[] mimeTypes = {"image/jpeg", "image/png"};
            //photopickerintent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            ((Activity)context).startActivityForResult(photopickerintent, GALLERY_REQUEST_CODE);
        }
        return selectedImage;
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == GALLERY_REQUEST_CODE) {
                selectedImage = data.getData(); //FileNotFoundException?
                //MainActivity.meme.setImageURI(selectedImage);
            }
        }
    }

    private void requestGalleryPermission() { //TODO lisää shouldShowRequestPermissionRationale jos tulee ongelmia
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, GALLERY_REQUEST_CODE);
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}
