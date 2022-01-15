package com.rudratej.everlist.ui
import com.rudratej.everlist.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudratej.everlist.db.task.Task

class CompletedTasksRVAdapter(private val context: Context, private val listener: ICompletedTaskRVAdapter):
    RecyclerView.Adapter<CompletedTasksRVAdapter.CompletedTaskViewHolder>() {

    private val allCompletedTasks=ArrayList<Task>()

    inner class CompletedTaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.ctitleTV)
        val deleteButton=itemView.findViewById<ImageView>(R.id.cdeleteBtn)
        val checkBox=itemView.findViewById<ImageView>(R.id.ccheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTasksRVAdapter.CompletedTaskViewHolder {
        val viewHolder=CompletedTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.completed_task_tile,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allCompletedTasks[viewHolder.adapterPosition])
        }
        viewHolder.checkBox.setOnClickListener {
            listener.onCheckClicked(allCompletedTasks[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CompletedTaskViewHolder, position: Int) {
        val currentTask=allCompletedTasks[position]
        holder.textView.text=currentTask.task
    }

    override fun getItemCount(): Int {
        return allCompletedTasks.size
    }

    fun updateList(newList:List<Task>){
        allCompletedTasks.clear()
        allCompletedTasks.addAll(newList)
        notifyDataSetChanged()
    }


}

interface ICompletedTaskRVAdapter{
    fun onItemClicked(task: Task)
    fun onCheckClicked(task: Task)
}