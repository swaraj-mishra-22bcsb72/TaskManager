package com.example.taskmanager

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanager.ui.screens.*
import com.example.taskmanager.viewmodel.AuthViewModel

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(viewModel = authViewModel) {
                navController.navigate("main") {
                    popUpTo("auth") { inclusive = true }
                }
            }
        }

        composable("main") {
            MainScreen(authViewModel = authViewModel, navController = navController)
        }
    }
}
