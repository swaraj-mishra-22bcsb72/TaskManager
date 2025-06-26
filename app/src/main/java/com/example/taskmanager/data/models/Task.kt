package com.example.taskmanager.data.models

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis()
)