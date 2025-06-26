package com.example.taskmanager.data.repositories

import com.example.taskmanager.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun register(email: String, password: String, user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            firestore.collection("users").document(user.email).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        val email = auth.currentUser?.email ?: return@withContext null
        val snapshot = firestore.collection("users").document(email).get().await()
        return@withContext snapshot.toObject(User::class.java)
    }

    suspend fun updateUser(user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val email = auth.currentUser?.email ?: return@withContext Result.failure(Exception("User not logged in"))
            firestore.collection("users").document(email).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteProfile(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val email = auth.currentUser?.email ?: return@withContext Result.failure(Exception("User not logged in"))
            firestore.collection("users").document(email).delete().await()
            auth.currentUser?.delete()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() = auth.signOut()
    fun getCurrentUserEmail(): String = auth.currentUser?.email ?: ""
}