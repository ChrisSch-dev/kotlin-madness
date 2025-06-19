package com.example.markdownconverter

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun markdownToHtml(md: String): String =
    md.replace(Regex("# (.*)"), "<h1>$1</h1>")
        .replace(Regex("## (.*)"), "<h2>$1</h2>")
        .replace(Regex("\\*\\*(.*)\\*\\*"), "<b>$1</b>")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var markdown by remember { mutableStateOf("") }
            var html by remember { mutableStateOf("") }
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = markdown,
                    onValueChange = { markdown = it },
                    label = { Text("Markdown") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = { html = markdownToHtml(markdown) }, modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Convert")
                }
                Text("HTML Output:", style = MaterialTheme.typography.labelLarge)
                Text(html)
            }
        }
    }
}