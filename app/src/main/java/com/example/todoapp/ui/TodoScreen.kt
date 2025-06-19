package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.Todo
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    var newTodo by remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.padding(16.dp)) {
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = newTodo,
                onValueChange = { newTodo = it },
                placeholder = { Text("Add a to-do...") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (newTodo.text.isNotBlank()) {
                    viewModel.addTodo(newTodo.text)
                    newTodo = TextFieldValue("")
                }
            }) { Text("Add") }
        }
        Spacer(Modifier.height(16.dp))
        if (todos.isEmpty()) {
            Text("Your list is empty! 🎉", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn {
                items(todos.size) { i ->
                    val todo = todos[i]
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(if (todo.isDone) Color(0xFFE0E0E0) else Color.Unspecified)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = todo.text,
                            color = if (todo.isDone) Color.Gray else Color.Unspecified,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { viewModel.toggleTodo(todo) }) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Done"
                            )
                        }
                        IconButton(onClick = { viewModel.deleteTodo(todo) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    }
                }
            }
        }
    }
}