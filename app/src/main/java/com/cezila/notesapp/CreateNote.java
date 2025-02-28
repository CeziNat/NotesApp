package com.cezila.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.cezila.notesapp.database.AppDatabase;
import com.cezila.notesapp.model.Note;
import com.cezila.notesapp.repository.NoteRepository;
import com.cezila.notesapp.repository.NoteRepositoryImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateNote extends AppCompatActivity {

    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "note-database").build();
        NoteRepository repository = new NoteRepositoryImpl(database.getNoteDao());

        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView btnSave = findViewById(R.id.btn_save);
        EditText etTitle = findViewById(R.id.et_title);
        EditText etNote = findViewById(R.id.et_note);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = etNote.getText().toString();
                String titleText = etTitle.getText().toString();

                if (titleText.isEmpty()) {
                    Toast.makeText(CreateNote.this, "Preencha o campo titulo", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note(titleText, noteText);
                Toast.makeText(CreateNote.this, "Nota criada com sucesso, titulo: " + note.getTitle(), Toast.LENGTH_SHORT).show();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        repository.saveNote(note);
                    }
                });
                finish();
            }
        });
    }

}