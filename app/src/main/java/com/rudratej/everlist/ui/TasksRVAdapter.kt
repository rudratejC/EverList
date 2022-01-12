package com.rudratej.everlist.ui
import com.rudratej.everlist.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudratej.everlist.Task

class TasksRVAdapter(private val context: Context, private val listener: ITaskRVAdapter):
    RecyclerView.Adapter<TasksRVAdapter.TaskViewHolder>() {

    private val allTasks=ArrayList<Task>()

    inner class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.titleTV)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteBtn)
        val checkBox=itemView.findViewById<ImageView>(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewHolder=TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.task_tile,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allTasks[viewHolder.adapterPosition])
        }
        viewHolder.checkBox.setOnClickListener {
            listener.onCheckClicked(allTasks[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask=allTasks[position]
        holder.textView.text=currentTask.task
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    fun updateList(newList:List<Task>){
        allTasks.clear()
        allTasks.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ITaskRVAdapter{
    fun onItemClicked(task: Task)
    fun onCheckClicked(task: Task)
}