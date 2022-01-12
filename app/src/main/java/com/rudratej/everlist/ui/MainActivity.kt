package com.rudratej.everlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rudratej.everlist.R
import com.rudratej.everlist.Task
import com.rudratej.everlist.TaskViewModel
import com.rudratej.everlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ITaskRVAdapter {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTasksBtn.setOnClickListener{
            startActivity(Intent(this,AddTask::class.java))
        }

        binding.completedBtn.setOnClickListener{
            startActivity(Intent(this,CompletedTasks::class.java))
        }

        val rv=findViewById<RecyclerView>(R.id.taskRV)
        rv.layoutManager=LinearLayoutManager(this)
        val adapter= TasksRVAdapter(this,this
        )
        rv.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            TaskViewModel::class.java)
        viewModel.alltasks.observe(this, Observer {
            it?.let{
                adapter.updateList(it)
            }
        })

    }
    override fun onItemClicked(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this,"${task
            .task} deleted successfully",Toast.LENGTH_LONG).show()
    }

    override fun onCheckClicked(task: Task) {
        task.isCompleted=true
        Toast.makeText(this,"${task.task} is marked done ", Toast.LENGTH_LONG).show()
        viewModel.checkTask(task)
    }
}