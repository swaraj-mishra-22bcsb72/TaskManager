package com.example.taskmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskmanager.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(authViewModel: AuthViewModel, mainNavController: NavHostController) {
    var showSaved by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        authViewModel.fetchUser()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Edit Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = authViewModel.age,
            onValueChange = { authViewModel.age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = authViewModel.sex,
            onValueChange = { authViewModel.sex = it },
            label = { Text("Sex") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = authViewModel.phone,
            onValueChange = { authViewModel.phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                authViewModel.updateUser {
                    showSaved = it
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Update Profile")
        }

        if (showSaved) {
            Text("Profile updated successfully", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                authViewModel.logout()
                mainNavController.navigate("auth") {
                    popUpTo("main") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Out")
        }

        Button(
            onClick = {
                authViewModel.deleteAccount { success ->
                    if (success) {
                        mainNavController.navigate("auth") {
                            popUpTo("main") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Delete Account", color = Color.White)
        }
    }
}