package com.example.meemigeniskuvatesti3;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static java.lang.System.out;


public class FileManager extends Activity {

    private int GALLERY_REQUEST_CODE = 1;
    private static FileManager instance = null;

    public void loadImage(Context context) { //uses context of MainActivity
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context);
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

    public void saveImage(Bitmap image, Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            try {
                String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String filename = "meme_" + time + ".jpg";
                ContentResolver resolver = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                Uri imageuri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                OutputStream os = resolver.openOutputStream(Objects.requireNonNull(imageuri));
                image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                Objects.requireNonNull(os).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestPermissions(Context context) { //TODO lisää shouldShowRequestPermissionRationale?
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions((Activity)context, permissions, GALLERY_REQUEST_CODE);
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}