package com.example.diary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diary.viewmodel.DiaryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DiaryScreen(viewModel: DiaryViewModel = viewModel()) {
    var entry by remember { mutableStateOf("") }
    val entries by viewModel.entries.collectAsState()

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = entry, onValueChange = { entry = it }, label = { Text("New Entry") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            if (entry.isNotBlank()) {
                viewModel.addEntry(entry)
                entry = ""
            }
        }, modifier = Modifier.padding(vertical = 8.dp)) { Text("Add Entry") }
        LazyColumn {
            items(entries.size) { i ->
                val e = entries[i]
                Text(e.content)
                Text("Date: " + java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(e.timestamp), style = MaterialTheme.typography.bodySmall)
                Divider()
            }
        }
    }
}