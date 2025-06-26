package com.example.taskmanager.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.models.Task
import com.example.taskmanager.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()
    val tasks = mutableStateListOf<Task>()

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                tasks.clear()
                tasks.addAll(repository.getTasks())
            } catch (e: Exception) {
                // log error or set error state
            }
        }
    }

    fun addTask(title: String, description: String) {
        val task = Task(title = title, description = description)
        viewModelScope.launch {
            repository.addTask(task)
            fetchTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            fetchTasks()
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
            fetchTasks()
        }
    }
}