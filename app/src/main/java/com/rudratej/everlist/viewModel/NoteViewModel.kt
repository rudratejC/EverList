package com.rudratej.everlist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rudratej.everlist.db.TaskRoomDatabase
import com.rudratej.everlist.db.note.Note
import com.rudratej.everlist.db.note.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val repository:NoteRepository
    val allNotes:LiveData<List<Note>>

    init {
        val dao= TaskRoomDatabase.getDatabase(application).noteDao()
        repository= NoteRepository(dao)
        allNotes=repository.allNotes
    }

    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}