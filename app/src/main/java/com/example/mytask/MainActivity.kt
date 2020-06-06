package com.example.mytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.adapter.TaskListAdapter
import com.example.mytask.model.Task
import com.example.mytask.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TaskListAdapter(this,listener)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //observe the task when change in task
        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let { adapter.setTasks(it) }
        })
    }

    private var listener = object : TaskListAdapter.OnItemClickListener {
        override fun onItemClick(task: Task) {
            if(task.taskStatus==1) {
                val intent = Intent(this@MainActivity, TaskDetailActivity::class.java)
                intent.putExtra(MY_TASK, task)
                startActivity(intent)
            }
        }
    }

    companion object{
        const val MY_TASK = "TASK"
    }

}
