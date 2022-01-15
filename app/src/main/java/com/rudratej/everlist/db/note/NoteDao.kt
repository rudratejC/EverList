package com.rudratej.everlist.db.note

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDao {
    @Query("Select * from note_table order by id desc")
    fun getAllNotes():LiveData<List<Note>>

    @Insert
    fun insert(note:Note)

    @Delete
    fun delete(note: Note)
}