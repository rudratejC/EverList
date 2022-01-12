package com.rudratej.everlist.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rudratej.everlist.R
import com.rudratej.everlist.TaskViewModel

class Fragment_Task : Fragment() {

    companion object {
        fun newInstance() = Fragment_Task()
    }

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_fragment, container, false)


        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

    }



}