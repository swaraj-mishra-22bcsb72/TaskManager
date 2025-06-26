package com.example.taskmanager.data.repositories

import com.example.taskmanager.data.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TaskRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserEmail(): String {
        return auth.currentUser?.email ?: throw IllegalStateException("User not logged in")
    }

    private fun getTasksRef(): CollectionReference {
        val email = getUserEmail()
        return firestore.collection("users")
            .document(email)
            .collection("tasks")
    }

    suspend fun addTask(task: Task) {
        val doc = getTasksRef().document()
        val newTask = task.copy(id = doc.id)
        doc.set(newTask).await()
    }

    suspend fun updateTask(task: Task) {
        getTasksRef().document(task.id).set(task).await()
    }

    suspend fun deleteTask(taskId: String) {
        getTasksRef().document(taskId).delete().await()
    }

    suspend fun getTasks(): List<Task> {
        return getTasksRef().get().await().toObjects(Task::class.java)
    }
}