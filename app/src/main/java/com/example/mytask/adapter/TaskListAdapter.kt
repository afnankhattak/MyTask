package com.example.mytask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.R
import com.example.mytask.model.Task


class TaskListAdapter internal constructor(
        context: Context,
        private var listener: OnItemClickListener
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var task = emptyList<Task>()


    interface OnItemClickListener {
        fun onItemClick(task: Task)
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val imageViewStatus: ImageView = itemView.findViewById(R.id.imageViewStatus)
        val textViewSNO: TextView = itemView.findViewById(R.id.textViewSNO)


        fun bindView(task: Task, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(task) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.item_recyclerview, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = task[position]
        holder.wordItemView.text = current.task
        holder.textViewSNO.text = StringBuilder().append(current.id).toString()

        if(current.taskStatus==1){
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_next);
        } else if(current.taskStatus==2){
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_cancel);
        }else {
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_done);
        }

            holder?.bindView(task[position], listener)

    }

    internal fun setTasks(task: List<Task>) {
        this.task = task
        notifyDataSetChanged()
    }

    override fun getItemCount() = task.size
}


