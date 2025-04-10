package com.fionasiregar0032.taxflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.fionasiregar0032.taxflow.navigation.NavGraph
import com.fionasiregar0032.taxflow.ui.theme.TaxflowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isDarkThemeEnabled = remember { mutableStateOf(false) }

            TaxflowTheme(darkTheme = isDarkThemeEnabled.value) {
                val navController = rememberNavController()
                NavGraph(navController = navController, onToggleTheme = {
                    isDarkThemeEnabled.value = !isDarkThemeEnabled.value
                })
            }
        }
    }
}



