package com.fionasiregar0032.taxflow.navigation

sealed class Screen(val route: String) {
    object Main : Screen("MainScreen")
    object Second : Screen("SecondScreen")
}