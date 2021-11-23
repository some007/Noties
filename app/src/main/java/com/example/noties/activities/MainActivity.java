package com.example.noties.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.noties.R;
import com.example.noties.database.NoteDatabase;
import com.example.noties.entities.Note;

import java.util.List;

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
        getNotes();
    }
    private void getNotes()
    {
        class GetNotesTask extends AsyncTask<Void,Void,List<Note>>
        {
            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NoteDatabase.getDatabase(getApplicationContext())
                        .noteDao().getAllNotes();

            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                Log.d("MY_NOTES",notes.toString());
            }
        }
        new GetNotesTask().execute();
    }
}