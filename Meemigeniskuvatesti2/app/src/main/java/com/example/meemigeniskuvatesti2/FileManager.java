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

import androidx.core.content.ContextCompat;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FileManager {

    private int GALLERY_REQUEST_CODE = 1;
    Uri selectedImage;
    private static FileManager instance = null;

    public Uri getImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
        PackageManager.PERMISSION_GRANTED) {
            Intent photopickerintent = new Intent(Intent.ACTION_PICK);
            photopickerintent.setType("image/*");
            String[] mimeTypes = {"image/jpeg", "image/png"};
            photopickerintent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(this, photopickerintent, GALLERY_REQUEST_CODE);
            return selectedImage;
        }
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == GALLERY_REQUEST_CODE) {
                selectedImage = data.getData();
            }
        }
    }*/

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}
