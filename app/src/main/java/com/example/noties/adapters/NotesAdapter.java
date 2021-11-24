package com.example.noties.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noties.R;
import com.example.noties.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>
{
    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (
                        R.layout.item_container_note,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position)
    {
        holder.setNote(notes.get(position));
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder
    {
        TextView textTitle,subTitle,textDatetime;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            subTitle = itemView.findViewById(R.id.textSubtitle);
            textDatetime = itemView.findViewById(R.id.textDatetime);

        }
        void setNote(Note note)
        {
            textTitle.setText(note.getTitle());
            if(note.getSubtitle().trim().isEmpty())
            {
                subTitle.setVisibility(View.GONE);
            }
            else {
                subTitle.setText(note.getSubtitle());
            }
            textDatetime.setText(note.getDatetime());
        }

    }
}
