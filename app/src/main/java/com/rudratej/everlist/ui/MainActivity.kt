package com.rudratej.everlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.rudratej.everlist.R
import com.rudratej.everlist.viewModel.TaskViewModel
import com.rudratej.everlist.databinding.ActivityMainBinding
import com.rudratej.everlist.ui.fragments.Fragment_Note
import com.rudratej.everlist.ui.fragments.Fragment_ShopList
import com.rudratej.everlist.ui.fragments.Fragment_Task


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskFragment=Fragment_Task(application)
        val noteFragment=Fragment_Note()
        val shopListFragment=Fragment_ShopList()

        setCurrentFragment(taskFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mi_notes -> {
                    setCurrentFragment(noteFragment)
                }
                R.id.mi_tasks -> {
                    setCurrentFragment(taskFragment)
                }
                R.id.mi_shopping_list->{
                    setCurrentFragment(shopListFragment)
                }

            }
            true
        }




//        binding.addTasksBtn.setOnClickListener{
//            startActivity(Intent(this,AddTask::class.java))
//        }
//
//        binding.completedBtn.setOnClickListener{
//            startActivity(Intent(this,CompletedTasks::class.java))
//        }
//
//        val rv=findViewById<RecyclerView>(R.id.taskRV)
//        rv.layoutManager=LinearLayoutManager(this)
//        val adapter= TasksRVAdapter(this,this
//        )
//        rv.adapter=adapter
//
//        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
//            TaskViewModel::class.java)
//        viewModel.alltasks.observe(this, Observer {
//            it?.let{
//                adapter.updateList(it)
//            }
//        })

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }

    public fun getVM():ViewModel{
        return viewModel
    }
//    override fun onItemClicked(task: Task) {
//        viewModel.deleteTask(task)
//        Toast.makeText(this,"${task
//            .task} deleted successfully",Toast.LENGTH_LONG).show()
//    }
//
//    override fun onCheckClicked(task: Task) {
//        task.isCompleted=true
//        Toast.makeText(this,"${task.task} is marked done ", Toast.LENGTH_LONG).show()
//        viewModel.checkTask(task)
//    }
}