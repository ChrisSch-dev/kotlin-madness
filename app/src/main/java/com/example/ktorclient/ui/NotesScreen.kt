package com.example.ktorclient.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ktorclient.viewmodel.NotesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel = viewModel()) {
    val notes by viewModel.notes.collectAsState()
    val error = viewModel.error

    Column(Modifier.padding(16.dp)) {
        Button(onClick = { viewModel.refresh() }) { Text("Fetch Notes") }
        if (error != null) Text("Error: $error", color = MaterialTheme.colorScheme.error)
        LazyColumn {
            items(notes.size) { i ->
                Column(Modifier.padding(vertical = 8.dp)) {
                    Text(notes[i].title, style = MaterialTheme.typography.titleMedium)
                    Text(notes[i].body, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}