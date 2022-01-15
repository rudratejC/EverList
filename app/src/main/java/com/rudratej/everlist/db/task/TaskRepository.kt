package com.rudratej.everlist.db.task

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks= taskDao.getAllTasks()
    val allCompletedTasks=taskDao.getAllCompletedTasks()

    suspend fun insert(task: Task){
        taskDao.insert(task)
    }

    suspend fun delete(task: Task){
        taskDao.delete(task)
    }

    suspend fun checkTask(task: Task){
        taskDao.checkTask(task)
    }

}