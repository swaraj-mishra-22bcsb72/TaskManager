package com.example.taskmanager.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.ui.components.BottomNavBar
import com.example.taskmanager.ui.components.BottomNavItem
import com.example.taskmanager.viewmodel.AuthViewModel

@Composable
fun MainScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = bottomNavController,
                items = listOf(BottomNavItem.Tasks, BottomNavItem.Profile)
            )
        }
    ) { padding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Tasks.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomNavItem.Tasks.route) {
                TasksScreen()
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen(authViewModel = authViewModel, mainNavController = navController)
            }
        }
    }
}