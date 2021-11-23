package com.example.noties.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.noties.R;

public class MainActivity extends AppCompatActivity {
     public static final int REQUEST_CODE_ADD_NOTE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageaddNote = findViewById(R.id.imageAddNotesMain);
        imageaddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(),CreateNoteActivity.class),
                        REQUEST_CODE_ADD_NOTE
                );
            }
        });
    }
}