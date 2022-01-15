package com.rudratej.everlist.db.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
class Task(
    @ColumnInfo(name="task") val task:String,
    @ColumnInfo(name="isCompleted") var isCompleted:Boolean
) {
    @PrimaryKey(autoGenerate = true) var id:Int=0
}