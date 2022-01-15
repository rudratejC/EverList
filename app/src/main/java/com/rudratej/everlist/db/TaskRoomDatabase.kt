package com.rudratej.everlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rudratej.everlist.db.note.Note
import com.rudratej.everlist.db.note.NoteDao
import com.rudratej.everlist.db.task.Task
import com.rudratej.everlist.db.task.TaskDao

@Database(entities = [Task::class, Note::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase():RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun noteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}