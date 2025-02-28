package com.cezila.notesapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cezila.notesapp.R;
import com.cezila.notesapp.model.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private ConstraintLayout constraintLayout;
    private final TextView tvTitle;
    private final TextView tvText;
    private final TextView tvDate;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.cl_item);
        tvTitle = itemView.findViewById(R.id.tv_item_title);
        tvText = itemView.findViewById(R.id.tv_item_text);
        tvDate = itemView.findViewById(R.id.tv_item_date);
    }

    public void bind(Note note) {
        tvTitle.setText(note.getTitle());
        tvText.setText(note.getText());
        tvDate.setText(note.getCreatedAt().toString());
    }

}
