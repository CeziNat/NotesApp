package com.cezila.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.cezila.notesapp.adapter.NoteAdapter;
import com.cezila.notesapp.database.AppDatabase;
import com.cezila.notesapp.model.Note;
import com.cezila.notesapp.repository.NoteRepository;
import com.cezila.notesapp.repository.NoteRepositoryImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Executor executor = Executors.newSingleThreadExecutor();
    private NoteAdapter noteAdapter;
    private LinearLayout llNotFound;
    private RecyclerView recyclerView;
    private NoteRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("##MainActivity", "Estamos no onCreate()...");

        noteAdapter = new NoteAdapter(new ArrayList<>());
        llNotFound = findViewById(R.id.ll_notes_not_found);

        recyclerView = findViewById(R.id.rr_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "note-database").build();
        repository = new NoteRepositoryImpl(database.getNoteDao());

        FloatingActionButton fabNewNote = findViewById(R.id.fab_create_note);
        fabNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCreateNoteScreen();
            }
        });

        loadNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("##MainActivity", "Estamos no onResume()...");
        loadNotes();
    }

    private void loadNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = repository.getAllNotes();

                // Voltando pra THREAT PRINCIPAL
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noteAdapter.setNoteList(notes);
                        noteAdapter.notifyDataSetChanged();

                        if(notes.isEmpty()) {
                            llNotFound.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            llNotFound.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });
    }

    private void navigateToCreateNoteScreen() {
        Intent intent = new Intent(MainActivity.this, CreateNote.class);
        startActivity(intent);
    }

}