package com.example.vko7_t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText textid;
    EditText nameid;
    String text = "";
    String name = "";
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textid = (EditText) findViewById(R.id.text);
        nameid = (EditText) findViewById(R.id.name);
        context = MainActivity.this;
    }

    public void writeFile(View v) {
        try {
            text = textid.getText().toString();
            name = nameid.getText().toString();
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(name, Context.MODE_PRIVATE));
            osw.write(text);
            osw.close();
        } catch (IOException e) {
            Log.e("IO Exception", "IO-virhe");
        } finally {
            System.out.println("Kirjoitus toimii!");
        }
    }

    public void readFile(View v) {
        try {
            name = nameid.getText().toString();
            InputStream is = context.openFileInput(name);
            Scanner scan = new Scanner(is);
            String line = "";
            while ( (line = scan.nextLine()) != null) {
                System.out.println(line);
                textid.setText(line);
            }
            scan.close();
        } catch (IOException e) {
            Log.e("IO Exception", "IO-virhe");
        } finally {
            System.out.println("Luku toimii!");
        }
    }
}
