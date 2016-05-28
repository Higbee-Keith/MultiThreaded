package com.byui.cs246.multithreaded;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String filename = "numbers.txt";
    File file;
    ArrayList<String> list;
    ArrayAdapter<String> numbersAdapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the Create button is clicked **/
    public void createFile(View view) throws IOException {
        file = new File(getFilesDir(), filename);
        BufferedWriter buf = new BufferedWriter(new FileWriter(file));

        /* Append to the file */
        try {
            for (int i = 1; i <= 10; i++) {
                try {
                    buf.write(i);
                    System.out.println(i);
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Called when the Load button is clicked **/
    public void loadFile(View view) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(file));

        list = new ArrayList<String>();

        // Read each line and pause
        try {
            for (String line = bufferedReader.readLine();
                 line != null; line = bufferedReader.readLine()) {
                list.add(line);
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add to List View using ArrayAdapter
        numbersAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_main, R.id.textView, list);

        // Connect ArrayAdapter to ListView to be populated
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(numbersAdapter);
    }

    /** Called when the Clear button is clicked **/
    public void clearList(View view) {
    /*
        1. clear the list. (Hint: Clear the ArrayAdapter)
    */
        list.clear();

    }
}
