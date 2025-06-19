package com.example.ktorclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember { mutableStateOf("Press to fetch notes") }
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val notes = try {
                        URL("https://jsonplaceholder.typicode.com/posts").readText()
                    } catch (e: Exception) {
                        e.message
                    }
                    withContext(Dispatchers.Main) { text = notes ?: "Error" }
                }
            }) {
                Text("Fetch Notes")
            }
            Text(text)
        }
    }
}