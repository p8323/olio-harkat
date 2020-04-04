package com.example.meemigeniskuvatesti3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FileManager extends Activity {

    private int GALLERY_REQUEST_CODE = 1;
    private static FileManager instance = null;

    public void loadImage(Context context) { //uses context of MainActivity
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestGalleryPermission(context);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Intent photopickerintent = new Intent(Intent.ACTION_PICK);
            photopickerintent.setType("image/*");
            //String[] mimeTypes = {"image/jpeg", "image/png"};
            //photopickerintent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            ((Activity)context).startActivityForResult(photopickerintent, GALLERY_REQUEST_CODE);
        }
    }

    private void requestGalleryPermission(Context context) { //TODO lisää shouldShowRequestPermissionRationale jos tulee ongelmia
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions((Activity)context, permissions, GALLERY_REQUEST_CODE);
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}