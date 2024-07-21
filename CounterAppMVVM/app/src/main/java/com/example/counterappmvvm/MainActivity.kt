package com.example.counterappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.counterappmvvm.ui.CounterApp
import com.example.counterappmvvm.ui.theme.CounterAppMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppMVVMTheme {
                val viewModel: Counter_ViewModel by viewModels()
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    CounterApp(viewModel)
                }
            }
        }
    }
}