package com.fionasiregar0032.taxflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fionasiregar0032.taxflow.screen.MainScreen
import com.fionasiregar0032.taxflow.screen.SecondScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route)
    {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(Screen.Second.route) {
            SecondScreen(navController)
        }
    }
}