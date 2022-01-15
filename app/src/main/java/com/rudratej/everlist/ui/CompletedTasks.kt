package com.rudratej.everlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rudratej.everlist.R
import com.rudratej.everlist.db.task.Task
import com.rudratej.everlist.viewModel.TaskViewModel

class CompletedTasks : AppCompatActivity(),ICompletedTaskRVAdapter{
    lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_tasks)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            TaskViewModel::class.java)

        val rv=findViewById<RecyclerView>(R.id.completedTaskRV)
        rv.layoutManager= LinearLayoutManager(this)
        val adapter= CompletedTasksRVAdapter(this,this)
        rv.adapter=adapter


        viewModel.allcompletedtasks.observe(this, Observer {
            it?.let{
                adapter.updateList(it)
            }
        })


    }
    override fun onItemClicked(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this,"${task
            .task} deleted successfully", Toast.LENGTH_LONG).show()
    }

    override fun onCheckClicked(task: Task) {
        task.isCompleted=false
        Toast.makeText(this,"${task.task} is marked undone ", Toast.LENGTH_SHORT).show()
        viewModel.checkTask(task)
    }
}