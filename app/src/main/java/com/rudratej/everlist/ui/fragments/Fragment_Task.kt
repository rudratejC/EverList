package com.rudratej.everlist.ui.fragments


import android.app.Application
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudratej.everlist.R
import com.rudratej.everlist.db.task.Task
import com.rudratej.everlist.viewModel.TaskViewModel
import com.rudratej.everlist.databinding.FragmentTaskFragmentBinding
import com.rudratej.everlist.ui.*

class Fragment_Task(val application: Application) : Fragment(R.layout.fragment_task_fragment), ITaskRVAdapter {
    lateinit var binding: FragmentTaskFragmentBinding
    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTaskFragmentBinding.bind(view)

        binding.addTasksBtn.setOnClickListener{
            startActivity(Intent(context, AddTask::class.java))

        }

        binding.completedBtn.setOnClickListener{
            startActivity(Intent(context, CompletedTasks::class.java))

        }

        val rv=binding.taskRV
        rv.layoutManager= LinearLayoutManager(context)
        val adapter= context?.let {
            TasksRVAdapter(
                it,this
            )
        }
        rv.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            TaskViewModel::class.java)
        viewModel.alltasks.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter?.updateList(it)
            }
        })

    }

    override fun onItemClicked(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(context,"${task
            .task} deleted successfully", Toast.LENGTH_LONG).show()
    }

    override fun onCheckClicked(task: Task) {
        task.isCompleted=true
        Toast.makeText(context,"${task.task} is marked done ", Toast.LENGTH_SHORT).show()
        viewModel.checkTask(task)
    }


}