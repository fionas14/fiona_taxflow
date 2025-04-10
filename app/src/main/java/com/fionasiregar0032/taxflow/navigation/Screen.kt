package com.fionasiregar0032.taxflow.navigation

sealed class Screen(val route: String) {
    object Home : Screen("HomeScreen")
    object Second : Screen("SecondScreen")
}