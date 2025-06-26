package com.example.taskmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.models.User
import com.example.taskmanager.data.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var age by mutableStateOf("")
    var sex by mutableStateOf("")
    var phone by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var isRegistering by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)

    fun onLoginClick() {
        viewModelScope.launch {
            val result = repository.login(email, password)
            isSuccess = result.isSuccess
            errorMessage = result.exceptionOrNull()?.message
            if (isSuccess) fetchUser()
        }
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            val user = User(email, age.toIntOrNull() ?: 0, sex, "+91$phone")
            val result = repository.register(email, password, user)
            isSuccess = result.isSuccess
            errorMessage = result.exceptionOrNull()?.message
        }
    }

    fun fetchUser() {
        viewModelScope.launch {
            val user = repository.getUser()
            user?.let {
                email = it.email
                age = it.age.toString()
                sex = it.sex
                phone = it.phone.replace("+91", "")
            }
        }
    }

    fun updateUser(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = User(email, age.toIntOrNull() ?: 0, sex, "+91$phone")
            val result = repository.updateUser(user)
            onComplete(result.isSuccess)
        }
    }

    fun deleteAccount(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.deleteProfile()
            onComplete(result.isSuccess)
        }
    }

    fun logout() = repository.logout()
}