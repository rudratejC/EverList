package com.rudratej.everlist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table where isCompleted=0 order by id ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table where isCompleted=1 order by id ASC")
    fun getAllCompletedTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun checkTask(task: Task)

}