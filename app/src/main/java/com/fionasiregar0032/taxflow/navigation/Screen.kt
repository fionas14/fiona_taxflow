package com.fionasiregar0032.taxflow.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("HomeScreen")
    data object Second : Screen("SecondScreen")
    data object About : Screen("AboutScreen")
    data object Settings : Screen("SettingScreen")
}