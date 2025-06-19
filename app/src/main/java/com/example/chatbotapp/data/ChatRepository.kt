package com.example.chatbotapp.data

class ChatRepository {
    fun getReply(input: String): String = when {
        "hello" in input.lowercase() -> "Hi there!"
        "how are you" in input.lowercase() -> "I'm a bot, I'm always good!"
        "bye" in input.lowercase() -> "Goodbye!"
        else -> "I don't understand."
    }
}