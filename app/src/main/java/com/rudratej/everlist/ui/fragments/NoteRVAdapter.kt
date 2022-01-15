package com.rudratej.everlist.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudratej.everlist.R
import com.rudratej.everlist.db.note.Note

class NoteRVAdapter(private val context: Context,private val listener: INoteRVAdapter): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes= ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteTitle=itemView.findViewById<TextView>(R.id.stag_Title)
        val noteDesc=itemView.findViewById<TextView>(R.id.stag_desc)
        val noteDelBtn=itemView.findViewById<ImageView>(R.id.noteDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_tile,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var currentNote=allNotes[position]
        holder.noteTitle.text=currentNote.title
        holder.noteDesc.text=currentNote.description
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}


interface INoteRVAdapter{
    fun onItemClicked(note:Note)
}