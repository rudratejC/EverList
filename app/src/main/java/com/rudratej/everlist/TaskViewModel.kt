package com.rudratej.everlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {

    val repository: TaskRepository
    val alltasks: LiveData<List<Task>>
    val allcompletedtasks: LiveData<List<Task>>

    init {
        val dao= TaskRoomDatabase.getDatabase(application).taskDao()
        repository= TaskRepository(dao)
        alltasks=repository.allTasks
        allcompletedtasks=repository.allCompletedTasks
    }

    fun deleteTask(task: Task)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun insertTask(task: Task)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun checkTask(task: Task)=viewModelScope.launch(Dispatchers.IO) {

        repository.checkTask(task)
    }

}