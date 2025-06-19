package com.example.chatbotapp

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
            var input by remember { mutableStateOf("") }
            var messages by remember { mutableStateOf(listOf<Pair<String, String>>()) }
            Column(Modifier.padding(16.dp)) {
                LazyColumn(Modifier.weight(1f)) {
                    items(messages.size) { i ->
                        Text("${messages[i].first}: ${messages[i].second}")
                    }
                }
                OutlinedTextField(value = input, onValueChange = { input = it }, label = { Text("Your message") })
                Button(onClick = {
                    val reply = when {
                        "hello" in input.lowercase() -> "Hi there!"
                        "how are you" in input.lowercase() -> "I'm a bot, I'm always good!"
                        "bye" in input.lowercase() -> "Goodbye!"
                        else -> "I don't understand."
                    }
                    messages = messages + ("You" to input) + ("Bot" to reply)
                    input = ""
                }) { Text("Send") }
            }
        }
    }
}