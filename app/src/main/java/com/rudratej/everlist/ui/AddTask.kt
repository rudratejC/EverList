package com.rudratej.everlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rudratej.everlist.R
import com.rudratej.everlist.db.task.Task
import com.rudratej.everlist.viewModel.TaskViewModel

class AddTask : AppCompatActivity() {

    lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val btn= findViewById<Button>(R.id.submitBtn)
        val et= findViewById<EditText>(R.id.input)
        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            TaskViewModel::class.java)
        btn.setOnClickListener {
            val taskText=et.text.toString()
            Log.d("tag",taskText)
            if(taskText.isNotEmpty()){
                viewModel.insertTask(Task(taskText,false))
                Toast.makeText(this,"$taskText added successfully", Toast.LENGTH_LONG).show()
                et.text.clear()
                finish()
            }

        }



    }


}