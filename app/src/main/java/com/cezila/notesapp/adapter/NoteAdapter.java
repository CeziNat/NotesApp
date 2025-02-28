package com.cezila.notesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cezila.notesapp.R;
import com.cezila.notesapp.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> notes) {
        this.noteList = notes;
    }

}
