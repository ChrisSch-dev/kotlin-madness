package com.example.markdownapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun markdownToHtml(markdown: String): String =
    markdown.replace(Regex("# (.*)"), "<h1>$1</h1>")
        .replace(Regex("## (.*)"), "<h2>$1</h2>")
        .replace(Regex("\\*\\*(.*)\\*\\*"), "<b>$1</b>")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var input by remember { mutableStateOf("") }
            var output by remember { mutableStateOf("") }
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(value = input, onValueChange = { input = it }, label = { Text("Markdown") })
                Button(onClick = { output = markdownToHtml(input) }) { Text("Convert") }
                Spacer(Modifier.height(16.dp))
                Text("HTML Output:\n$output")
            }
        }
    }
}