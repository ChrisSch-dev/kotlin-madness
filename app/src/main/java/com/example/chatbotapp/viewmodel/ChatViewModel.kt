package com.example.chatbotapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatbotapp.data.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel : ViewModel() {
    private val repo = ChatRepository()
    private val _messages = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val messages: StateFlow<List<Pair<String, String>>> = _messages

    fun sendMessage(input: String) {
        val reply = repo.getReply(input)
        _messages.value = _messages.value + ("You" to input) + ("Bot" to reply)
    }
}