package com.example.chatbotapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatbotapp.viewmodel.ChatViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    var input by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()

    Column(Modifier.padding(16.dp).fillMaxSize()) {
        LazyColumn(Modifier.weight(1f)) {
            items(messages.size) { i ->
                Text("${messages[i].first}: ${messages[i].second}")
            }
        }
        OutlinedTextField(value = input, onValueChange = { input = it }, label = { Text("Your message") })
        Button(onClick = {
            if (input.isNotBlank()) {
                viewModel.sendMessage(input)
                input = ""
            }
        }, modifier = Modifier.padding(top = 8.dp)) { Text("Send") }
    }
}