package com.example.mytask.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mytask.dao.TaskDao
import com.example.mytask.model.Task


class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(task: Task) {
        taskDao.update(task)
    }
}
