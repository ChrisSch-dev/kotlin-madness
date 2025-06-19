package com.example.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var entry by remember { mutableStateOf("") }
            var entries by remember { mutableStateOf(listOf<String>()) }
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(value = entry, onValueChange = { entry = it }, label = { Text("New Entry") })
                Button(onClick = {
                    if (entry.isNotBlank()) {
                        entries = entries + entry
                        entry = ""
                    }
                }) { Text("Add Entry") }
                Spacer(Modifier.height(16.dp))
                LazyColumn {
                    items(entries.size) { i -> Text(entries[i]) }
                }
            }
        }
    }
}