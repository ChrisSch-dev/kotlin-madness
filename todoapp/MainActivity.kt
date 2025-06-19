package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var todos by remember { mutableStateOf(listOf<String>()) }
            var input by remember { mutableStateOf("") }
            Column(Modifier.padding(16.dp)) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    if (input.isNotBlank()) {
                        todos = todos + input
                        input = ""
                    }
                }) { Text("Add Todo") }
                Spacer(Modifier.height(16.dp))
                LazyColumn {
                    items(todos.size) { i ->
                        Row(
                            Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(todos[i])
                            IconButton(onClick = {
                                todos = todos.toMutableList().apply { removeAt(i) }
                            }) { Icon(Icons.Default.Delete, contentDescription = "Delete") }
                        }
                    }
                }
            }
        }
    }
}