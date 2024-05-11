package com.example.note

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context:Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleTextureView:TextView = itemView.findViewById(R.id.titleTextview)
        val contentTextureView:TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton:ImageView = itemView.findViewById(R.id.UpdateButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val note = notes[position]
        holder.titleTextureView.text = note.title
        holder.contentTextureView.text= note.content

        holder.updateButton.setOnClickListener{
            val intent =Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}