package com.example.mytask.dao

import androidx.lifecycle.LiveData;
import androidx.room.*
import com.example.mytask.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * from task_table ORDER BY task ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAll()
}
