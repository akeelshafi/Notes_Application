package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    Context context;
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
     holder.titleTextview.setText(note.title);
     holder.contentTextView.setText(note.content);
     holder.timestmapTextView.setText(Utility.timestampTostring(note.timestamp));

     holder.itemView.setOnClickListener((v)->{
         Intent intent = new Intent(context,Note_Details_Activity.class);
         intent.putExtra("title",note.title);
         intent.putExtra("content",note.content);
         String docId = this.getSnapshots().getSnapshot(position).getId();
         intent.putExtra("docId",docId);

         context.startActivity(intent);
     });


    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
    return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextview,contentTextView,timestmapTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextview = itemView.findViewById(R.id.notes_title_text_view);
            contentTextView = itemView.findViewById(R.id.notes_content_text_view);
            timestmapTextView = itemView.findViewById(R.id.notes_timestamp_text_view);
        }
    }
}
