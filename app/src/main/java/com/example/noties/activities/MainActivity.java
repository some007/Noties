package com.example.noties.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.noties.R;
import com.example.noties.adapters.NotesAdapter;
import com.example.noties.database.NoteDatabase;
import com.example.noties.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     public static final int REQUEST_CODE_ADD_NOTE = 1;
     private RecyclerView recyclerView;
     private List<Note> noteList;
     private NotesAdapter notesAdapter;
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
        recyclerView = findViewById(R.id.notesRecyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        recyclerView.setAdapter(notesAdapter);

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
                if(noteList.size() == 0)
                {
                    noteList.addAll(notes);
                    notesAdapter.notifyDataSetChanged();
                }
                else {
                    noteList.add(0,notes.get(0));
                    notesAdapter.notifyItemInserted(0);
                }
                recyclerView.smoothScrollToPosition(0);
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_CODE_ADD_NOTE && resultCode ==RESULT_OK)
        {
            getNotes();
        }
    }
}