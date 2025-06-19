package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Question(val question: String, val options: List<String>, val answer: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val questions = listOf(
                Question("Capital of France?", listOf("Paris", "London", "Berlin"), 0),
                Question("2+2?", listOf("3", "4", "5"), 1)
            )
            var current by remember { mutableStateOf(0) }
            var score by remember { mutableStateOf(0) }
            var finished by remember { mutableStateOf(false) }
            if (finished) {
                Text("Quiz Finished! Score: $score/${questions.size}")
            } else {
                val q = questions[current]
                Column(Modifier.padding(16.dp)) {
                    Text(q.question)
                    Spacer(Modifier.height(8.dp))
                    q.options.forEachIndexed { idx, opt ->
                        Button(onClick = {
                            if (idx == q.answer) score++
                            if (current == questions.lastIndex) finished = true
                            else current++
                        }, Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                            Text(opt)
                        }
                    }
                }
            }
        }
    }
}