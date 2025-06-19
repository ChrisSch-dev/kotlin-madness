package com.example.fileorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var files by remember { mutableStateOf(listOf("a.txt", "b.jpg", "c.txt", "d.pdf")) }
            var organized by remember { mutableStateOf("") }
            Button(onClick = {
                val map = files.groupBy { it.substringAfterLast('.', "other") }
                organized = map.entries.joinToString("\n") { (ext, fs) -> "$ext: ${fs.joinToString()}" }
            }) { Text("Organize Files") }
            Text(organized)
        }
    }
}