package com.example.mytask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import com.example.mytask.MainActivity.Companion.MY_TASK
import com.example.mytask.model.Task
import com.example.mytask.viewmodel.TaskViewModel

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var checkBoxTaskCompleted: CheckBox
    private lateinit var button: Button
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val task = intent.getSerializableExtra(MY_TASK) as Task
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        checkBoxTaskCompleted = findViewById(R.id.checkBox)
        button = findViewById(R.id.button_save)

        checkBoxTaskCompleted.text="Is "+task.task+" Completed ?"

        checkBoxTaskCompleted.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button.isEnabled = true
                button.isClickable = true
            } else {
                button.isEnabled = false
                button.isClickable = false
            }
        }

        button.setOnClickListener {
            val replyIntent = Intent()
            if (checkBoxTaskCompleted.isChecked) {

                val task = Task(task.id, task.task, 3)
                taskViewModel.update(task)
                val newTask = Task(task.id + 1, "Task " + (task.id + 1), 1)
                taskViewModel.update(newTask)

            } else {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            finish()
        }
    }
}


