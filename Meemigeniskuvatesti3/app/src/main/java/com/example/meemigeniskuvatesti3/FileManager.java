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

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FileManager extends Activity {

    private int GALLERY_REQUEST_CODE = 1;
    private static FileManager instance = null;
    private Context context;

    public FileManager(Context maincontext) { //this class gets context from MainActivity
        context = maincontext;
    }

    public void loadImage() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            //String[] mimeTypes = {"image/jpeg", "image/png"};
            //photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            ((Activity)context).startActivityForResult(photoPickerIntent, GALLERY_REQUEST_CODE);
        }
    }

    public void saveImage(Bitmap image) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            try {
                String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "meme_" + time + ".jpg"; //filename is unique with date on it
                ContentResolver resolver = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                OutputStream os = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                Objects.requireNonNull(os).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestPermissions() { //TODO lisää shouldShowRequestPermissionRationale?
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE}; //write permission includes reading
        ActivityCompat.requestPermissions((Activity)context, permissions, GALLERY_REQUEST_CODE);
    }

    public static FileManager getInstance(Context mainContext) { //singleton
        if (instance == null) {
            instance = new FileManager(mainContext);
        }
        return instance;
    }

}