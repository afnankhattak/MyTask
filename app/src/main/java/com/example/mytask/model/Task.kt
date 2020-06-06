package com.example.mytask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_table")
data class Task  (
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "task") val task: String?,
        @ColumnInfo(name = "isTaskCompleted") val taskStatus: Int?
):Serializable
