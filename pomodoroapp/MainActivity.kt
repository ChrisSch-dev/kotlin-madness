package com.example.pomodoroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var status by remember { mutableStateOf("Ready") }
            var running by remember { mutableStateOf(false) }
            Button(onClick = {
                running = true
                CoroutineScope(Dispatchers.Main).launch {
                    status = "Work for 25 minutes"
                    delay(25 * 60 * 1000) // Use 2500L for demo
                    status = "Break for 5 minutes"
                    delay(5 * 60 * 1000) // Use 500L for demo
                    status = "Session Complete"
                    running = false
                }
            }, enabled = !running) { Text("Start Pomodoro") }
            Text(status)
        }
    }
}