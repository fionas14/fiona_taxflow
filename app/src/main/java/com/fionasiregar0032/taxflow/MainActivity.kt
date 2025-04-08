package com.fionasiregar0032.taxflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fionasiregar0032.taxflow.screen.MainScreen
import com.fionasiregar0032.taxflow.ui.theme.TaxflowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaxflowTheme {
            }
            MainScreen()
        }
    }
}

