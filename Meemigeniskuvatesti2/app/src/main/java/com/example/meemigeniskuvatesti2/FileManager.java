package com.example.meemigeniskuvatesti2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class FileManager {

    private int GALLERY_REQUEST_CODE = 1;
    private Uri selectedImage = null;
    private static FileManager instance = null;

    public Uri getImage(Context context) { //this method uses context from MainActivity
        if (ContextCompat.checkSelfPermission((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestGalleryPermission(context);
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
                selectedImage = data.getData();
            }
        }
    }

    private void requestGalleryPermission(Context context) { //TODO lisää shouldShowRequestPermissionRationale jos tulee ongelmia
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions((Activity) context, permissions, GALLERY_REQUEST_CODE);
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}